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
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.palo.it.forge.maven.plugins.jenkins.models.TemplateName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * ServiceTemplating
 * 
 * @author pguillerm
 * @since 5 août 2014
 */
public class ServiceTemplating implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long              serialVersionUID = 4622393081055400330L;

    private static final Logger            LOGGER           = LoggerFactory.getLogger(ServiceTemplating.class);

    private static final ServiceTemplating INSTANCE         = new ServiceTemplating();

    private Configuration                  cfg;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    private ServiceTemplating() {
        super();
        initializeFreemarker("/templates");
    }

    public static synchronized ServiceTemplating getInstance() {
        return INSTANCE;
    }

    private void initializeFreemarker(String path) {
        cfg = new Configuration();
        // pour que les stacks n'apparaissent pas dans la page mais soient
        // catched par le renderer
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // Répertoire des template dans le classpath

        cfg.setClassForTemplateLoading(this.getClass(), path);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.addAutoInclude("macros/commons.ftl");

    }

    // =========================================================================
    // RENDER
    // =========================================================================
    /**
     * Render.
     * 
     * @param templateName the template name
     * @param context the context
     * @return the string
     * @throws TemplateException the template exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public synchronized String render(final TemplateName templateName, final Map<String, Object> context)
            throws TemplateException, IOException {
        if (templateName == null) {
            throw new IOException("template musn't be null !");
        }
        return render(templateName.getName(), context);
    }

    /**
     * Render.
     * 
     * @param templateName the template name
     * @param context the context
     * @return the string
     * @throws TemplateException the template exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public synchronized String render(final String templateName, final Map<String, Object> context)
            throws TemplateException, IOException {
        final StringWriter writer = new StringWriter();
        render(writer, templateName, context);

        return writer.toString();
    }

    /**
     * Render.
     * 
     * @param out the out
     * @param templateName the template name
     * @param context the context
     * @throws TemplateException the template exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public synchronized void render(final Writer out, final String templateName, final Map<String, Object> context)
            throws TemplateException, IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("render begin: template= {} ", templateName);
        }
        if (out == null) {
            LOGGER.error("writer is null!");
            throw new IOException("write is null");
        }
        final Template template = getTemplate(templateName);
        if (template != null) {
            template.process(context, out);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("render end  : template= {} ", templateName);
        }
    }

    /**
     * Gets the template.
     * 
     * @param templateName the template name
     * @return the template
     */
    protected Template getTemplate(final String templateName) {
        Template result = null;
        if (templateName != null) {
            try {
                result = cfg.getTemplate(templateName);
            } catch (IOException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.error(e.getMessage(), e);
                } else {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return result;
    }
}
