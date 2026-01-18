package me.cocos.ignite.template;

import me.cocos.ignite.template.gradle.*;
import me.cocos.ignite.template.java.*;

public class TemplateFactory {

    private final GradleTemplate rootBuildGradle;
    private final GradleTemplate settingsGradle;
    private final GradleTemplate coreBuildGradle;
    private final GradleTemplate lwjgl3BuildGradle;
    private final JavaTemplate coreGame;
    private final JavaTemplate lwjgl3Launcher;

    public TemplateFactory() {
        // GRADLE
        this.rootBuildGradle = new RootBuildGradleTemplate();
        this.settingsGradle = new SettingsGradleTemplate();
        this.coreBuildGradle = new CoreBuildGradleTemplate();
        this.lwjgl3BuildGradle = new Lwjgl3BuildGradleTemplate();

        // JAVA
        this.coreGame = new CoreGameTemplate();
        this.lwjgl3Launcher = new Lwjgl3LauncherTemplate();
    }

    public GradleTemplate getRootBuildGradle() {
        return rootBuildGradle;
    }

    public GradleTemplate getSettingsGradle() {
        return settingsGradle;
    }

    public GradleTemplate getCoreBuildGradle() {
        return coreBuildGradle;
    }

    public GradleTemplate getLwjgl3BuildGradle() {
        return lwjgl3BuildGradle;
    }

    public JavaTemplate getCoreGame() {
        return coreGame;
    }

    public JavaTemplate getLwjgl3Launcher() {
        return lwjgl3Launcher;
    }
}
