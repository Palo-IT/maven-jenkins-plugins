package org.palo.it.forge.maven.plugins.jenkins.actions;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;
import org.palo.it.forge.maven.plugins.jenkins.api.ProjectHelper;
import org.palo.it.forge.maven.plugins.jenkins.exceptions.MavenJenkinsPluginsException;
import org.palo.it.forge.maven.plugins.jenkins.models.ProjectInfoJenkins;
import org.palo.it.forge.maven.plugins.jenkins.services.JenkinsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @goal delete
 * @author pguillerm
 * @since 30 juil. 2014
 */
@Mojo(name = "delete", defaultPhase = LifecyclePhase.VERIFY, threadSafe = true)
public class MojoDeleteJobs extends AbstractMojo {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MojoDeleteJobs.class);

    /**
     * POM.
     *
     * @parameter expression="${project}"
     * @readonly 
     * @required 
     */
    protected MavenProject      project;

    /** The project helper. */
    protected ProjectHelper     projectHelper;

    /**
     * The jenkins url.
     *
     * @parameter default-value=="${project.groupId}"
     * @required 
     * @readonly 
     */
    private String              jenkinsUrl;

    // =========================================================================
    // METHODS
    // =========================================================================
    
      /* (non-Javadoc)
       * @see org.apache.maven.plugin.Mojo#execute()
       */
       
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
            JenkinsService.getInstance().deleteJobs(info);
        } catch (MavenJenkinsPluginsException e) {
            LOGGER.error(e.getMessage(), e);
            throw new MojoFailureException(e.getMessage(), e);
        }
    }


}
