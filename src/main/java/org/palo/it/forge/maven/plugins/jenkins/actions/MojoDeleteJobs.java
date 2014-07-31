package org.palo.it.forge.maven.plugins.jenkins.actions;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.palo.it.forge.maven.plugins.jenkins.services.JenkinsService;

/**
 * @goal delete
 * @author pguillerm
 * @since 30 juil. 2014
 */
@Mojo(name = "delete", defaultPhase = LifecyclePhase.VERIFY, threadSafe = true)
public class MojoDeleteJobs extends AbstractMojoCommons {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Override
    protected void executePlugin(ProjectInfoJenkins info) throws MavenJenkinsPluginsException {
        JenkinsService.getInstance().deleteJobs(info);
    }

}
