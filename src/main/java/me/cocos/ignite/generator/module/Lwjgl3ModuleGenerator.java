package me.cocos.ignite.generator.module;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.TemplateFactory;
import me.cocos.ignite.util.FileWriter;
import me.cocos.ignite.util.PathResolver;

import java.io.IOException;
import java.nio.file.Path;

public record Lwjgl3ModuleGenerator(TemplateFactory templates) implements ModuleGenerator {

    @Override
    public void generate(Path projectRoot, ProjectConfig config) throws IOException {
        Path lwjglDir = projectRoot.resolve("lwjgl3");
        FileWriter.createDirectory(lwjglDir);

        Path buildFile = PathResolver.resolveBuildFile(lwjglDir);
        FileWriter.write(buildFile, templates.getLwjgl3BuildGradle().generate(config));

        Path srcPath = PathResolver.resolveSourcePath(lwjglDir, config.packageName())
                .resolve("lwjgl3");
        Path launcherFile = srcPath.resolve("Lwjgl3Launcher.java");
        FileWriter.write(launcherFile, templates.getLwjgl3Launcher().generate(config));
    }
}
