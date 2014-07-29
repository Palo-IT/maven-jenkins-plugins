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
package org.palo.it.forge.maven.plugins.jenkins.exceptions;

/**
 * MavenJenkinsPluginsException.
 * 
 * @author pguillerm
 * @since 29 juil. 2014
 */
public class MavenJenkinsPluginsException extends Exception {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1125315727478611324L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new maven jenkins plugins exception.
     */
    public MavenJenkinsPluginsException() {
        super();
    }

    /**
     * Instantiates a new maven jenkins plugins exception.
     * 
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public MavenJenkinsPluginsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new maven jenkins plugins exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public MavenJenkinsPluginsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new maven jenkins plugins exception.
     * 
     * @param message the message
     */
    public MavenJenkinsPluginsException(String message) {
        super(message);
    }

    /**
     * Instantiates a new maven jenkins plugins exception.
     * 
     * @param cause the cause
     */
    public MavenJenkinsPluginsException(Throwable cause) {
        super(cause);
    }

}
