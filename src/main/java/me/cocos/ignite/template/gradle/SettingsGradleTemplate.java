package me.cocos.ignite.template.gradle;

import me.cocos.ignite.model.ProjectConfig;
import java.util.stream.Collectors;

public class SettingsGradleTemplate implements GradleTemplate {

    @Override
    public String generate(ProjectConfig config) {
        String moduleIncludes = config.modules().stream()
                .map(module -> "'%s'".formatted(module.getDirectoryName()))
                .collect(Collectors.joining(", "));

        return """
               rootProject.name = '%s'
               include 'core', %s
               """.formatted(config.gameName(), moduleIncludes);
    }
}