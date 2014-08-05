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
  
  <#if project.scmType==GIT>
  <scm class="hudson.plugins.git.GitSCM" plugin="git@2.0.3">
    <configVersion>2</configVersion>
    <userRemoteConfigs>
      <hudson.plugins.git.UserRemoteConfig>
        <url>${project.scmConnection}</url>
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
  <triggers/>
  <concurrentBuild>false</concurrentBuild>


  <builders>
  
    <hudson.tasks.Maven>
      <targets>clean package</targets>
      <mavenName>maven</mavenName>
      <usePrivateRepository>false</usePrivateRepository>
      <settings class="jenkins.mvn.DefaultSettingsProvider"/>
      <globalSettings class="jenkins.mvn.DefaultGlobalSettingsProvider"/>
    </hudson.tasks.Maven>
    
    <hudson.tasks.Maven>
      <targets>deploy</targets>
      <mavenName>(Default)</mavenName>
      <usePrivateRepository>false</usePrivateRepository>
      <settings class="jenkins.mvn.DefaultSettingsProvider"/>
      <globalSettings class="jenkins.mvn.DefaultGlobalSettingsProvider"/>
    </hudson.tasks.Maven>
  </builders>
  
  
  <publishers>
    <hudson.tasks.Mailer plugin="mailer@1.8">
      <recipients><#if project.users?has_content><#list project.users as user>${user.email} </#list></#if></recipients>
      <dontNotifyEveryUnstableBuild>false</dontNotifyEveryUnstableBuild>
      <sendToIndividuals>false</sendToIndividuals>
    </hudson.tasks.Mailer>
  </publishers>

  
  
  <buildWrappers/>
</project>
