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
package org.palo.it.forge.maven.plugins.jenkins.actions;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.palo.it.forge.maven.plugins.jenkins.api.ProjectHelper;
import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.palo.it.forge.maven.plugins.jenkins.services.JenkinsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractMojoCommons
 * 
 * @author pguillerm
 * @since 30 juil. 2014
 */
public abstract class AbstractMojoCommons extends AbstractMojo {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMojoCommons.class);

    /**
     * POM
     * 
     * @parameter expression="${project}"
     * @readonly
     * @required
     */
    protected MavenProject      project;

    protected ProjectHelper     projectHelper;

    /**
     * @parameter default-value=="${project.groupId}"
     * @required
     * @readonly
     */
    private String              jenkinsUrl;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        projectHelper = new ProjectHelper(project);

        //@formatter:off
        final ProjectInfoJenkins info = new ProjectInfoJenkins( jenkinsUrl,
                                                                project.getGroupId(),
                                                                project.getArtifactId(),
                                                                true,
                                                                projectHelper.getScmType());
        //@formatter:on
        try {
            executePlugin(info);
        } catch (MavenJenkinsPluginsException e) {
            LOGGER.error(e.getMessage(), e);
            throw new MojoFailureException(e.getMessage(), e);
        }

    }

    protected abstract void executePlugin(ProjectInfoJenkins info) throws MavenJenkinsPluginsException;

    // =========================================================================
    // METHODS
    // =========================================================================

    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
}
