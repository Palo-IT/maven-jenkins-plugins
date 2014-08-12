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

/**
 * Templates
 * 
 * @author pguillerm
 * @since 5 août 2014
 */
public enum Templates implements TemplateName {
    template_ci("ci"), template_release("release"), template_snapshot("snapshot");

    private String shortName;

    private Templates(final String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String getName() {
        return name() + ".ftl";
    }

    public String getShortName() {
        return shortName;
    }
}
