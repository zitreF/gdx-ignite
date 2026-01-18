package me.cocos.ignite.generator.module;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.TemplateFactory;
import me.cocos.ignite.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;

public record RootProjectGenerator(TemplateFactory templates) implements ModuleGenerator {

    @Override
    public void generate(Path projectRoot, ProjectConfig config) throws IOException {
        FileWriter.write(
                projectRoot.resolve("build.gradle"),
                templates.getRootBuildGradle().generate(config)
        );

        FileWriter.write(
                projectRoot.resolve("settings.gradle"),
                templates.getSettingsGradle().generate(config)
        );

        FileWriter.createDirectory(projectRoot.resolve("assets"));
    }
}