package org.palo.it.forge.maven.plugins.jenkins.actions;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.palo.it.forge.maven.plugins.jenkins.services.JenkinsService;

@Mojo(name = "update", defaultPhase = LifecyclePhase.VERIFY, threadSafe = true)
public class MojoUpdateJobs extends AbstractMojoCommons {

    @Override
    protected void run(ProjectInfoJenkins info) throws MavenJenkinsPluginsException {
        JenkinsService.getInstance().createJobs(info);
    }

}
