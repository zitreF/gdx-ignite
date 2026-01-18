package me.cocos.ignite.generator;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.roots.ProjectRootManager;
import me.cocos.ignite.generator.platform.CoreModuleGenerator;
import me.cocos.ignite.generator.platform.RootProjectGenerator;
import me.cocos.ignite.model.ModuleType;
import me.cocos.ignite.model.ProjectConfig;

import java.nio.file.Path;

@Service(Service.Level.PROJECT)
public final class ProjectGenerator {

    private final Project project;
    private final CoreModuleGenerator coreModuleGenerator;
    private final RootProjectGenerator rootProjectGenerator;

    public ProjectGenerator(Project project) {
        this.project = project;
        this.coreModuleGenerator = new CoreModuleGenerator();
        this.rootProjectGenerator = new RootProjectGenerator();
    }

    public void generate(ProjectConfig config) {
        WriteCommandAction.runWriteCommandAction(project, "Igniting LibGDX Project", "Ignite Group", () -> {
            try {
                Path projectRoot = Path.of(config.destinationPath());

                coreModuleGenerator.generate(projectRoot, config);
                rootProjectGenerator.generate(projectRoot, config);

                for (ModuleType moduleType : config.modules()) {
                    moduleType.getGeneratorFactory().generate(projectRoot, config);
                }
            } catch (Exception e) {
                throw new RuntimeException("Ignite failed to ignite project", e);
            }
        });
    }
}