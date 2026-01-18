package me.cocos.ignite.generator;

import me.cocos.ignite.generator.module.CoreModuleGenerator;
import me.cocos.ignite.generator.module.RootProjectGenerator;
import me.cocos.ignite.model.ModuleType;
import me.cocos.ignite.template.TemplateFactory;

public class ModuleGeneratorFactory {

    private final TemplateFactory templates;

    public ModuleGeneratorFactory() {
        this.templates = new TemplateFactory();
    }

    public ModuleGenerator createRootGenerator() {
        return new RootProjectGenerator(templates);
    }

    public ModuleGenerator createCoreGenerator() {
        return new CoreModuleGenerator(templates);
    }

    public ModuleGenerator createModuleGenerator(ModuleType moduleType) {
        return moduleType.createGenerator(templates);
    }
}