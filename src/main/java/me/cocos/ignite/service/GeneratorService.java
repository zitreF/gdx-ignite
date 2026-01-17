package me.cocos.ignite.service;

import me.cocos.ignite.model.ModuleType;
import me.cocos.ignite.model.ProjectConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class GeneratorService {

    private final TemplateFactory templates;

    public GeneratorService() {
        this.templates = new TemplateFactory();
    }

    public void generate(ProjectConfig config) {
        try {
            Path root = Path.of(config.destinationPath());
            this.createFile(root.resolve("build.gradle"), templates.getRootBuildGradle(config));
            this.createFile(root.resolve("settings.gradle"), templates.getSettingsGradle(config));

            Files.createDirectories(root.resolve("assets"));
            this.generateCore(root, config);

            if (config.modules().contains(ModuleType.DESKTOP)) {
                this.generateLwjgl3(root, config);
            }
            // TODO: Android/iOS/HTML
        } catch (IOException e) {
            throw new RuntimeException("Ignite failed to ignite project", e);
        }
    }

    private void generateCore(Path root, ProjectConfig config) throws IOException {
        Path coreDir = root.resolve("core");
        Files.createDirectories(coreDir);
        this.createFile(coreDir.resolve("build.gradle"), templates.getCoreBuildGradle());

        Path srcPath = coreDir.resolve("src/main/java").resolve(config.packageName().replace('.', '/'));
        Files.createDirectories(srcPath);
        this.createFile(srcPath.resolve(config.mainClass() + ".java"), templates.getCoreGameJava(config));
    }

    private void generateLwjgl3(Path root, ProjectConfig config) throws IOException {
        Path lwjglDir = root.resolve("lwjgl3");
        Files.createDirectories(lwjglDir);
        this.createFile(lwjglDir.resolve("build.gradle"), templates.getLwjgl3BuildGradle(config.packageName()));

        Path srcPath = lwjglDir.resolve("src/main/java").resolve(config.packageName().replace('.', '/')).resolve("lwjgl3");
        Files.createDirectories(srcPath);
        this.createFile(srcPath.resolve("Lwjgl3Launcher.java"), templates.getLwjgl3LauncherJava(config));
    }

    private void createFile(Path path, String content) throws IOException {
        Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}