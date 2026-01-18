package me.cocos.ignite.template.gradle;

import me.cocos.ignite.model.ProjectConfig;

public class CoreBuildGradleTemplate implements GradleTemplate {

    @Override
    public String generate(ProjectConfig config) {
        return """
               [JavaPlugin, EclipsePlugin, IdeaPlugin].forEach { apply plugin: it }
               
               sourceCompatibility = 17
               targetCompatibility = 17
               
               eclipse.project.name = appName + "-core"
               
               dependencies {
                   api "com.badlogicgames.gdx:gdx:$gdxVersion"
               }
               """;
    }
}