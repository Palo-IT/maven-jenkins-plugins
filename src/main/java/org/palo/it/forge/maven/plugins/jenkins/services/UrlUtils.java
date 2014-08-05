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

import java.io.Serializable;

/**
 * UrlUtils
 * 
 * @author pguillerm
 * @since 4 août 2014
 */
public class UrlUtils implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long     serialVersionUID = 5056016972783180410L;

    private static final UrlUtils INSTANCE         = new UrlUtils();

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    private UrlUtils() {
        super();
    }

    public static synchronized UrlUtils getInstance() {
        return INSTANCE;
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to normalize URL
     * 
     * @param url the url
     * @return the string
     */
    public String normalize(final String url) {
        String result = null;
        if (url != null && !url.isEmpty()) {
            final String rawResult = url.trim();
            if ("/".equals(rawResult)) {
                return "";
            } else if (rawResult.endsWith("/")) {
                result = rawResult.substring(0, rawResult.length() - 1);
            } else {
                result = url;
            }
        }
        return result;
    }
}
