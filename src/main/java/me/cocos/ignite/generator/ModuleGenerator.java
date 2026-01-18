package me.cocos.ignite.generator;

import me.cocos.ignite.model.ProjectConfig;

import java.io.IOException;
import java.nio.file.Path;

@FunctionalInterface
public interface ModuleGenerator {

    void generate(Path projectRoot, ProjectConfig config) throws IOException;

}
