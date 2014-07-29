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
 *   Links:
 *   ======
 *   Git      : https://github.com/Palo-IT/Devoxx2014Raspberry
 */
package org.palo.it.forge.maven.plugins.jenkins.services;

import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
