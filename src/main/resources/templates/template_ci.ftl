<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description></description>
  
  
  <logRotator class="hudson.tasks.LogRotator">
    <daysToKeep>-1</daysToKeep>
    <numToKeep>20</numToKeep>
    <artifactDaysToKeep>-1</artifactDaysToKeep>
    <artifactNumToKeep>-1</artifactNumToKeep>
  </logRotator>
  
  
  <keepDependencies>false</keepDependencies>
  
  <properties/>
 
 <#if forge.project.scm.type=='GIT'>
  <scm class="hudson.plugins.git.GitSCM" plugin="git@2.0.3">
    <configVersion>2</configVersion>
    <userRemoteConfigs>
      <hudson.plugins.git.UserRemoteConfig>
        <url>${forge.project.scm.connection}</url>
        <credentialsId>${forge.project.scm.credentialsId}</credentialsId>
      </hudson.plugins.git.UserRemoteConfig>
    </userRemoteConfigs>
    <branches>
      <hudson.plugins.git.BranchSpec>
        <name>*/master</name>
      </hudson.plugins.git.BranchSpec>
    </branches>
    <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
    <submoduleCfg class="list"/>
    <extensions/>
  </scm>
  </#if>
  
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
  
  
  <#if forge.project.scm.type=='GIT'>
  <triggers>
    <com.cloudbees.jenkins.GitHubPushTrigger plugin="github@1.8">
      <spec></spec>
    </com.cloudbees.jenkins.GitHubPushTrigger>
    <hudson.triggers.TimerTrigger>
      <spec>*/5 * * * *</spec>
    </hudson.triggers.TimerTrigger>
    <hudson.triggers.SCMTrigger>
      <spec>* * * * *</spec>
      <ignorePostCommitHooks>false</ignorePostCommitHooks>
    </hudson.triggers.SCMTrigger>
  </triggers>
  </#if>
  
  <concurrentBuild>false</concurrentBuild>
  
  
  <builders>
    <hudson.tasks.Maven>
      <targets>clean package</targets>
      <mavenName>maven</mavenName>
      <usePrivateRepository>false</usePrivateRepository>
      <settings class="jenkins.mvn.FilePathSettingsProvider">
        <path>${maven.home.settings.xml}</path>
      </settings>
      <globalSettings class="jenkins.mvn.FilePathGlobalSettingsProvider">
        <path>${maven.home.settings.xml}</path>
      </globalSettings>
    </hudson.tasks.Maven>
  </builders>
  
  
   <publishers>
    <hudson.tasks.Mailer plugin="mailer@1.8">
      <recipients>${project.users.mails}</recipients>
      <dontNotifyEveryUnstableBuild>false</dontNotifyEveryUnstableBuild>
      <sendToIndividuals>false</sendToIndividuals>
    </hudson.tasks.Mailer>
  </publishers>

  
  <buildWrappers>
    <hudson.plugins.ws__cleanup.PreBuildCleanup plugin="ws-cleanup@0.21">
      <deleteDirs>false</deleteDirs>
      <cleanupParameter></cleanupParameter>
      <externalDelete></externalDelete>
    </hudson.plugins.ws__cleanup.PreBuildCleanup>
  </buildWrappers>
</project>
