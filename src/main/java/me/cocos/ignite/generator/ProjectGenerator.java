package me.cocos.ignite.generator;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.Service;
import me.cocos.ignite.model.ProjectConfig;

import java.nio.file.Path;

@Service(Service.Level.PROJECT)
public final class ProjectGenerator {

    private final ModuleGeneratorFactory generatorFactory;
    private final Project project;

    public ProjectGenerator(Project project) {
        this.project = project;
        this.generatorFactory = new ModuleGeneratorFactory();
    }

    public void generate(ProjectConfig config) {
        WriteCommandAction.runWriteCommandAction(project, "Igniting LibGDX Project", "Ignite Group", () -> {
            try {
                Path projectRoot = Path.of(config.destinationPath());

                generatorFactory.createRootGenerator().generate(projectRoot, config);
                generatorFactory.createCoreGenerator().generate(projectRoot, config);

                for (var moduleType : config.modules()) {
                    generatorFactory.createModuleGenerator(moduleType).generate(projectRoot, config);
                }
            } catch (Exception e) {
                throw new RuntimeException("Ignite failed to ignite project", e);
            }
        });
    }
}