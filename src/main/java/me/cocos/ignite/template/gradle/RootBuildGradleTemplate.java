package me.cocos.ignite.template.gradle;

import me.cocos.ignite.model.ModuleType;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.Template;

public class RootBuildGradleTemplate implements Template {

    @Override
    public String generate(ProjectConfig config) {
        boolean hasAndroid = config.modules().contains(ModuleType.ANDROID);
        boolean hasIos = config.modules().contains(ModuleType.IOS);
        boolean hasHtml = config.modules().contains(ModuleType.HTML);

        return """
               buildscript {
                   repositories {
                       mavenCentral()
                       google()
                       gradlePluginPortal()
                       maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
                   }
                   dependencies {
                       %s
                       %s
                       %s
                   }
               }
               
               allprojects {
                   apply plugin: 'eclipse'
                   apply plugin: 'idea'
               
                   version = '1.0.0'
                   ext {
                       appName = '%s'
                       gdxVersion = '1.14.0'
                       roboVMVersion = '2.3.20'
                       teavmVersion = '0.9.2'
                   }
               
                   repositories {
                       mavenCentral()
                       google()
                       maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
                       maven { url 'https://jitpack.io' }
                   }
               }
               
               project(':core') {
                   apply plugin: 'java-library'

                   dependencies {
                       api "com.badlogicgames.gdx:gdx:$gdxVersion"
                   }
               }
               """.formatted(
                hasAndroid ? "classpath 'com.android.tools.build:gradle:8.1.0'" : "",
                hasIos ? "classpath 'com.mobidevelop.robovm:robovm-gradle-plugin:2.3.20'" : "",
                hasHtml ? "classpath 'org.teavm:teavm-gradle-plugin:0.9.2'" : "",
                config.gameName()
        );
    }
}