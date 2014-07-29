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
package org.palo.it.forge.maven.plugins.jenkins.models;

import org.palo.it.forge.maven.plugins.jenkins.api.ScmType;

/**
 * ProjectInfoJenkins
 * 
 * @author pguillerm
 * @since 29 juil. 2014
 */
public final class ProjectInfoJenkins {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    private String  jenkinsUrl;

    private String  groupId;

    private String  artifactId;

    private boolean isTrunk = true;

    private ScmType scmType;

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

}
