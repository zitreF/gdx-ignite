package me.cocos.ignite.service;

import me.cocos.ignite.model.ModuleType;
import me.cocos.ignite.model.ProjectConfig;
import java.util.stream.Collectors;

public class TemplateFactory {

    public String getSettingsGradle(ProjectConfig config) {
        String moduleIncludes = config.modules().stream()
                .map(module -> "'%s'".formatted(module.getDirectoryName()))
                .collect(Collectors.joining(", "));

        return """
               rootProject.name = '%s'
               include 'core', %s
               """.formatted(config.gameName(), moduleIncludes);
    }

    public String getRootBuildGradle(ProjectConfig config) {
        return """
               buildscript {
                   repositories {
                       mavenCentral()
                       google()
                       gradlePluginPortal()
                       maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
                   }
                   dependencies {
                       if (%b) classpath "com.android.tools.build:gradle:8.1.0"
                       if (%b) classpath "com.mobidevelop.robovm:robovm-gradle-plugin:2.3.20"
                       if (%b) classpath "org.teavm:teavm-gradle-plugin:0.9.2"
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
                   }
               
                   repositories {
                       mavenCentral()
                       google()
                       maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
                       maven { url 'https://jitpack.io' }
                   }
               }
               """.formatted(
                config.modules().contains(ModuleType.ANDROID),
                config.modules().contains(ModuleType.IOS),
                config.modules().contains(ModuleType.HTML),
                config.gameName()
        );
    }

    public String getCoreBuildGradle() {
        return """
               [JavaPlugin, EclipsePlugin, IdeaPlugin].forEach { apply plugin: it }
               
               dependencies {
                   implementation "com.badlogicgames.gdx:gdx:$gdxVersion"
               }
               """;
    }

    public String getLwjgl3BuildGradle(String packageName) {
        return """
               apply plugin: 'application'
               
               sourceSets.main.resources.srcDirs += [ rootProject.file('assets').path ]
               mainClassName = '%s.lwjgl3.Lwjgl3Launcher'
               eclipse.project.name = appName + "-lwjgl3"
               
               dependencies {
                   implementation project(':core')
                   implementation "com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion"
                   implementation "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop"
               }
               """.formatted(packageName);
    }

    public String getCoreGameJava(ProjectConfig config) {
        return """
               package %s;
               
               import com.badlogic.gdx.Game;
               import com.badlogic.gdx.graphics.g2d.SpriteBatch;
               import com.badlogic.gdx.graphics.Color;
               import com.badlogic.gdx.utils.ScreenUtils;
               
               public class %s extends Game {
                   public SpriteBatch batch;
               
                   @Override
                   public void create() {
                       this.batch = new SpriteBatch();
                   }
               
                   @Override
                   public void render() {
                       ScreenUtils.clear(Color.BLACK);
                       super.render();
                   }
               
                   @Override
                   public void dispose() {
                       this.batch.dispose();
                   }
               }
               """.formatted(config.packageName(), config.mainClass());
    }

    public String getLwjgl3LauncherJava(ProjectConfig config) {
        return """
               package %s.lwjgl3;
               
               import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
               import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
               import %s.%s;
               
               public class Lwjgl3Launcher {
                   public static void main(String[] args) {
                       this.createApplication();
                   }
               
                   private static Lwjgl3Application createApplication() {
                       return new Lwjgl3Application(new %s(), this.getDefaultConfiguration());
                   }
               
                   private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
                       Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
                       configuration.setTitle("%s");
                       configuration.useVsync(true);
                       configuration.setForegroundFPS(60);
                       configuration.setWindowedMode(640, 480);
                       return configuration;
                   }
               }
               """.formatted(config.packageName(), config.packageName(), config.mainClass(), config.mainClass(), config.gameName());
    }
}