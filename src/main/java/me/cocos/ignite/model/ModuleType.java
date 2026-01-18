package me.cocos.ignite.model;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.generator.platform.Lwjgl3ModuleGenerator;

public enum ModuleType {
    DESKTOP("lwjgl3", new Lwjgl3ModuleGenerator()),
    ANDROID("android", null),
    IOS("ios", null),
    HTML("html", null);

    private final String directoryName;
    private final ModuleGenerator generatorFactory;

    ModuleType(String directoryName, ModuleGenerator generatorFactory) {
        this.directoryName = directoryName;
        this.generatorFactory = generatorFactory;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public ModuleGenerator getGeneratorFactory() {
        return generatorFactory;
    }
}