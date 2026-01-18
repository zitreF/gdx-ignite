package me.cocos.ignite.generator.platform;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.TemplateFactory;
import me.cocos.ignite.template.gradle.RootBuildGradleTemplate;
import me.cocos.ignite.template.gradle.SettingsGradleTemplate;
import me.cocos.ignite.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;

public class RootProjectGenerator implements ModuleGenerator {

    @Override
    public void generate(Path projectRoot, ProjectConfig config) throws IOException {
        FileWriter.write(
                projectRoot.resolve("build.gradle"),
                TemplateFactory.generate(RootBuildGradleTemplate.class, config)
        );

        FileWriter.write(
                projectRoot.resolve("settings.gradle"),
                TemplateFactory.generate(SettingsGradleTemplate.class, config)
        );

        FileWriter.createDirectory(projectRoot.resolve("assets"));
    }
}