package me.cocos.ignite.template.gradle;

import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.Template;
import me.cocos.ignite.util.SdkVersionParser;

public class CoreBuildGradleTemplate implements Template {

    @Override
    public String generate(ProjectConfig config) {
        String sdkVersion = SdkVersionParser.getJavaVersion(config.sdk());
        return """
               [JavaPlugin, EclipsePlugin, IdeaPlugin].forEach { apply plugin: it }
               
               sourceCompatibility = %s
               targetCompatibility = %s
               
               eclipse.project.name = appName + "-core"
               
               dependencies {
                   api "com.badlogicgames.gdx:gdx:$gdxVersion"
               }
               """.formatted(sdkVersion, sdkVersion);
    }
}