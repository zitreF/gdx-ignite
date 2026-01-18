package me.cocos.ignite.template.java;

import me.cocos.ignite.model.ProjectConfig;

public class Lwjgl3LauncherTemplate implements JavaTemplate {

    @Override
    public String generate(ProjectConfig config) {
        return """
               package %s.lwjgl3;
               
               import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
               import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
               import %s.%s;
               
               public class Lwjgl3Launcher {
                   
                   public static void main(String[] args) {
                       createApplication();
                   }
               
                   private static Lwjgl3Application createApplication() {
                       return new Lwjgl3Application(new %s(), getDefaultConfiguration());
                   }
               
                   private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
                       Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
                       configuration.setTitle("%s");
                       configuration.useVsync(true);
                       configuration.setForegroundFPS(60);
                       configuration.setWindowedMode(1280, 720);
                       return configuration;
                   }
               }
               """.formatted(
                config.packageName(),
                config.packageName(),
                config.mainClass(),
                config.mainClass(),
                config.gameName()
        );
    }
}
