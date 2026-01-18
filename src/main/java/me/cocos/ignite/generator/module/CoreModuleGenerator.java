package me.cocos.ignite.generator.module;

import me.cocos.ignite.generator.ModuleGenerator;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.TemplateFactory;
import me.cocos.ignite.util.FileWriter;
import me.cocos.ignite.util.PathResolver;

import java.io.IOException;
import java.nio.file.Path;

public record CoreModuleGenerator(TemplateFactory templates) implements ModuleGenerator {

    @Override
    public void generate(Path projectRoot, ProjectConfig config) throws IOException {
        Path coreDir = projectRoot.resolve("core");
        FileWriter.createDirectory(coreDir);

        Path buildFile = PathResolver.resolveBuildFile(coreDir);
        FileWriter.write(buildFile, templates.getCoreBuildGradle().generate(config));

        Path srcPath = PathResolver.resolveSourcePath(coreDir, config.packageName());
        Path mainClassFile = srcPath.resolve(config.mainClass() + ".java");
        FileWriter.write(mainClassFile, templates.getCoreGame().generate(config));
    }
}
