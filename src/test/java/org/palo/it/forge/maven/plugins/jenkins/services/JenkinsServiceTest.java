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
 */
package org.palo.it.forge.maven.plugins.jenkins.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.palo.it.forge.maven.plugins.jenkins.api.ScmType;
import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.MavenUser;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JenkinsServiceTest
 * 
 * @author pguillerm
 * @since 5 août 2014
 */
public class JenkinsServiceTest {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsServiceTest.class);

    // =========================================================================
    // METHODS
    // =========================================================================
    @Test
    public void testCreateJobs() throws MavenJenkinsPluginsException {
        final ProjectInfoJenkins info = new ProjectInfoJenkins();
        info.setJenkinsUrl("http://forge.palo-it.com/ci");
        info.setGroupId("org.palo.it.forge.sandbox");
        info.setArtifactId("test-project");
        info.setScmType(ScmType.GIT);
        info.setTrunk(true);

        info.setScmConnection("git@github.com:Palo-IT/maven-jenkins-plugins.git");
        info.setScmCredentialsId("foobar");
        info.setMavenHomeSetting("/home/ci/maven/settings.xml");

        List<MavenUser> users = new ArrayList<MavenUser>();
        users.add(new MavenUser("foo@bar.org", "jenkins"));

        info.setUsers(users);

        JenkinsService.getInstance().createJobs(info);
    }
}
