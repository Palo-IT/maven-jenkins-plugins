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
  
  <scm class="hudson.scm.NullSCM"/>
  
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
  <triggers/>
  
  <concurrentBuild>false</concurrentBuild>
  
  
  <builders>
    <hudson.tasks.Shell>
      <command>git clone ${forge.project.scm.connection}</command>
    </hudson.tasks.Shell>
    <hudson.tasks.Shell>
      <command>cd ${project.artifactId}; mvn clean package deploy; mvn release:clean release:prepare release:perform --batch-mode</command>
    </hudson.tasks.Shell>
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
