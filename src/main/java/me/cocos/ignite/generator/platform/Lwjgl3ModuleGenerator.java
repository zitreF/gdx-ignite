package me.cocos.ignite.generator.platform;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.TemplateFactory;
import me.cocos.ignite.template.gradle.Lwjgl3BuildGradleTemplate;
import me.cocos.ignite.template.java.Lwjgl3LauncherTemplate;
import me.cocos.ignite.util.FileWriter;
import me.cocos.ignite.util.PathResolver;

import java.io.IOException;
import java.nio.file.Path;

public class Lwjgl3ModuleGenerator implements ModuleGenerator {

    @Override
    public void generate(Path projectRoot, ProjectConfig config) throws IOException {
        Path lwjglDir = projectRoot.resolve("lwjgl3");

        FileWriter.createDirectory(lwjglDir);

        Path buildFile = PathResolver.resolveBuildFile(lwjglDir);

        FileWriter.write(
                buildFile,
                TemplateFactory.generate(Lwjgl3BuildGradleTemplate.class, config)
        );

        Path srcPath = PathResolver.resolveSourcePath(lwjglDir, config.packageName())
                .resolve("lwjgl3");
        Path launcherFile = srcPath.resolve("Lwjgl3Launcher.java");

        FileWriter.write(
                launcherFile,
                TemplateFactory.generate(Lwjgl3LauncherTemplate.class, config)
        );
    }
}
