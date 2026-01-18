package me.cocos.ignite.generator.platform;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.TemplateFactory;
import me.cocos.ignite.template.gradle.CoreBuildGradleTemplate;
import me.cocos.ignite.template.java.CoreGameTemplate;
import me.cocos.ignite.util.FileWriter;
import me.cocos.ignite.util.PathResolver;

import java.io.IOException;
import java.nio.file.Path;

public class CoreModuleGenerator implements ModuleGenerator {

    @Override
    public void generate(Path projectRoot, ProjectConfig config) throws IOException {
        Path coreDir = projectRoot.resolve("core");
        FileWriter.createDirectory(coreDir);

        Path buildFile = PathResolver.resolveBuildFile(coreDir);

        FileWriter.write(
                buildFile,
                TemplateFactory.generate(CoreBuildGradleTemplate.class, config)
        );

        Path srcPath = PathResolver.resolveSourcePath(coreDir, config.packageName());
        Path mainClassFile = srcPath.resolve(config.mainClass() + ".java");

        FileWriter.write(
                mainClassFile,
                TemplateFactory.generate(CoreGameTemplate.class, config)
        );
    }
}
