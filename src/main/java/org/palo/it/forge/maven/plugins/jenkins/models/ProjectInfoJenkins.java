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
package org.palo.it.forge.maven.plugins.jenkins.models;

import java.io.Serializable;
import java.util.List;

import org.palo.it.forge.maven.plugins.jenkins.api.ScmType;

/**
 * ProjectInfoJenkins
 * 
 * @author pguillerm
 * @since 29 juil. 2014
 */
public final class ProjectInfoJenkins implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6184410141480889066L;

    private String            jenkinsUrl;

    private String            groupId;

    private String            artifactId;

    private String            apiPath          = "/ci/createItem";

    private boolean           isTrunk          = true;

    private ScmType           scmType;

    private String            scmConnection;

    private String            scmCredentialsId;

    private String            mavenHomeSetting = "/home/ci/maven/settings.xml";

    private List<MavenUser>   users;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    public ProjectInfoJenkins() {
        super();
    }

    public ProjectInfoJenkins(final String jenkinsUrl, final String groupId, final String artifactId,
            final boolean isTrunk, final ScmType scmType) {
        super();
        this.jenkinsUrl = jenkinsUrl;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.isTrunk = isTrunk;
        this.scmType = scmType;
    }

    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // TODO:add hash code, equals and toString

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public boolean isTrunk() {
        return isTrunk;
    }

    public void setTrunk(boolean isTrunk) {
        this.isTrunk = isTrunk;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public ScmType getScmType() {
        return scmType;
    }

    public void setScmType(ScmType scmType) {
        this.scmType = scmType;
    }

    public String getScmConnection() {
        return scmConnection;
    }

    public void setScmConnection(String scmConnection) {
        this.scmConnection = scmConnection;
    }

    public String getScmCredentialsId() {
        return scmCredentialsId;
    }

    public void setScmCredentialsId(String scmCredentialsId) {
        this.scmCredentialsId = scmCredentialsId;
    }

    public String getMavenHomeSetting() {
        return mavenHomeSetting;
    }

    public void setMavenHomeSetting(String mavenHomeSetting) {
        this.mavenHomeSetting = mavenHomeSetting;
    }

    public List<MavenUser> getUsers() {
        return users;
    }

    public void setUsers(List<MavenUser> users) {
        this.users = users;
    }

}
