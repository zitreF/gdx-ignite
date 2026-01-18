package me.cocos.ignite.model;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.generator.module.Lwjgl3ModuleGenerator;
import me.cocos.ignite.template.TemplateFactory;

import java.util.function.Function;

public enum ModuleType {
    DESKTOP("lwjgl3", Lwjgl3ModuleGenerator::new),
    ANDROID("android", (deps) -> (root, config) -> {
        throw new UnsupportedOperationException("Android module not yet implemented");
    }),
    IOS("ios", (deps) -> (root, config) -> {
        throw new UnsupportedOperationException("iOS module not yet implemented");
    }),
    HTML("html", (deps) -> (root, config) -> {
        throw new UnsupportedOperationException("HTML module not yet implemented");
    });

    private final String directoryName;
    private final Function<TemplateFactory, ModuleGenerator> generatorFactory;

    ModuleType(String directoryName, Function<TemplateFactory, ModuleGenerator> generatorFactory) {
        this.directoryName = directoryName;
        this.generatorFactory = generatorFactory;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public ModuleGenerator createGenerator(TemplateFactory templateFactory) {
        return generatorFactory.apply(templateFactory);
    }
}