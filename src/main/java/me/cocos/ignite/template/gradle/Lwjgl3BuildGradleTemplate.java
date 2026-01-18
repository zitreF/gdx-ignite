package me.cocos.ignite.template.gradle;

import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.Template;
import me.cocos.ignite.util.SdkVersionParser;

public class Lwjgl3BuildGradleTemplate implements Template {

    @Override
    public String generate(ProjectConfig config) {
        String sdkVersion = SdkVersionParser.getJavaVersion(config.sdk());
        return """
               apply plugin: 'application'

               sourceCompatibility = %s
               targetCompatibility = %s

               sourceSets.main.resources.srcDirs += [ rootProject.file('assets').path ]
               mainClassName = '%s.lwjgl3.Lwjgl3Launcher'
               eclipse.project.name = appName + "-lwjgl3"

               dependencies {
                   implementation project(':core')
                   implementation "com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion"
                   implementation "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop"
               }
               
               run {
                   workingDir = rootProject.file('assets')
                   setIgnoreExitValue(true)
               }
               
               jar {
                   archiveFileName = "${appName}-${version}.jar"
                   duplicatesStrategy(DuplicatesStrategy.EXCLUDE)

                   from { configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) } }

                   manifest {
                       attributes 'Main-Class': project.mainClassName
                   }
               }
               """.formatted(
                       sdkVersion,
                       sdkVersion,
                       config.packageName()
        );
    }
}