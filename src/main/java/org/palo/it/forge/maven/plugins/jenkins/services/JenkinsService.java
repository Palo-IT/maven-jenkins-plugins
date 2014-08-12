/*
 *   PALO IT source code:
 *   ======================
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *  
        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.palo.it.forge.maven.plugins.jenkins.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.palo.it.forge.maven.plugins.jenkins.api.ScmType;
import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.palo.it.forge.maven.plugins.jenkins.models.Templates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.TemplateException;

/**
 * JenkinsService.
 * 
 * @author pguillerm
 * @since 29 juil. 2014
 */
public class JenkinsService {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger         LOGGER   = LoggerFactory.getLogger(JenkinsService.class);

    /** The Constant INSTANCE. */
    private static final JenkinsService INSTANCE = new JenkinsService();

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new jenkins service.
     */
    private JenkinsService() {
        super();
    }

    /**
     * Gets the single instance of JenkinsService.
     * 
     * @return single instance of JenkinsService
     */
    public static synchronized JenkinsService getInstance() {
        return INSTANCE;
    }

    // =========================================================================
    // PUBLICS
    // =========================================================================
    /**
     * Allo to creates jenkins jobs (continuous, snapshot and release)
     * 
     * @param info the info
     * @throws MavenJenkinsPluginsException the maven jenkins plugins exception
     */
    public void createJobs(final ProjectInfoJenkins info) throws MavenJenkinsPluginsException {
        assertInfo(info);
        final Map<String, Object> context = new HashMap<String, Object>();
        context.put("project", info);
        context.put("GIT", ScmType.GIT);
        context.put("SVN", ScmType.SVN);

        for (Templates template : Templates.values()) {
            final String templateRender = renderTemplate(template, context);
            try {
                postJenkinsJob(template, templateRender, info);
            } catch (ClientProtocolException e) {
                throw new MavenJenkinsPluginsException(e.getMessage(), e);
            } catch (IOException e) {
                throw new MavenJenkinsPluginsException(e.getMessage(), e);
            }
        }

    }

    protected String renderTemplate(final Templates template, final Map<String, Object> context)
            throws MavenJenkinsPluginsException {
        String result = null;
        try {
            result = ServiceTemplating.getInstance().render(template, context);
        } catch (TemplateException e) {
            throw new MavenJenkinsPluginsException(e.getMessage(), e);
        } catch (IOException e) {
            throw new MavenJenkinsPluginsException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * allow to update jenkins jobs
     * 
     * @param info the info
     * @throws MavenJenkinsPluginsException the maven jenkins plugins exception
     */
    public void updateJobs(final ProjectInfoJenkins info) throws MavenJenkinsPluginsException {
        assertInfo(info);
    }

    /**
     * allow to delete jenkins jobs
     * 
     * @param info the info
     * @throws MavenJenkinsPluginsException the maven jenkins plugins exception
     */
    public void deleteJobs(final ProjectInfoJenkins info) throws MavenJenkinsPluginsException {
        assertInfo(info);
    }

    // =========================================================================
    // ASSERT
    // =========================================================================
    /**
     * Assert info.
     * 
     * @param info the info
     * @throws MavenJenkinsPluginsException the maven jenkins plugins exception
     */
    protected void assertInfo(final ProjectInfoJenkins info) throws MavenJenkinsPluginsException {
        if (null == info) {
            throw new MavenJenkinsPluginsException("maven information musn't be null !");
        }

        if (null == info.getArtifactId()) {
            throw new MavenJenkinsPluginsException("artifact id musn't be null !");
        }

        if (null == info.getGroupId()) {
            throw new MavenJenkinsPluginsException("group id  musn't be null !");
        }

        if (null == info.getJenkinsUrl()) {
            throw new MavenJenkinsPluginsException("jenkins URL musn't be null !");
        }
    }

    // =========================================================================
    // PROTECTED
    // =========================================================================

    /**
     * Post jenkins job.
     * 
     * @param template the template
     * @param templateRender the template render
     * @param info the info
     * @throws MavenJenkinsPluginsException the maven jenkins plugins exception
     * @throws IOException
     * @throws ClientProtocolException
     */
    protected void postJenkinsJob(final Templates template, final String templateRender, final ProjectInfoJenkins info)
            throws MavenJenkinsPluginsException, ClientProtocolException, IOException {

        final URI urlJenkins = makeUrlForCreateJob(info, template);
        if (urlJenkins == null) {
            throw new MavenJenkinsPluginsException("jenkins url musn't be null");
        }

        //@formatter:off
        final CredentialsProvider credentials = new BasicCredentialsProvider();
        credentials.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(info.getJenkinsLogin(), info.getJenkinsPassword()));
        
        final CloseableHttpClient httpClient = HttpClientBuilder.create()
                                                                .setDefaultCredentialsProvider(credentials)
                                                                .build();
        
        final RequestConfig requestConfig = RequestConfig.custom()
                                                         .setConnectionRequestTimeout(1000)
                                                         .setConnectTimeout(1000)
                                                         .setSocketTimeout(1000)
                                                         .build();
        //@formatter:on

        final HttpPost httpPostRequest = new HttpPost(urlJenkins);
        httpPostRequest.setConfig(requestConfig);
        httpPostRequest.setEntity(new ByteArrayEntity(templateRender.getBytes(), ContentType.APPLICATION_XML));

        HttpResponse response = httpClient.execute(httpPostRequest);
        
        LOGGER.info("response : {}",response);
        

    }

    private URI makeUrlForCreateJob(ProjectInfoJenkins info, final Templates template) {
        URI result = null;
        final StringBuilder rawResult = new StringBuilder();
        rawResult.append(UrlUtils.getInstance().normalize(info.getJenkinsUrl()));
        rawResult.append(UrlUtils.getInstance().normalize(info.getApiPath()));
        rawResult.append("?name=");
        rawResult.append(info.getGroupId());
        rawResult.append("_");
        rawResult.append(info.getArtifactId());
        rawResult.append("_");
        rawResult.append(template.getShortName());

        try {
            result = new URI(rawResult.toString());
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}
