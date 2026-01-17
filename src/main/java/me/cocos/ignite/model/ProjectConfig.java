package me.cocos.ignite.model;

import com.intellij.openapi.projectRoots.Sdk;

import java.util.Set;

public record ProjectConfig(
        String gameName,
        String packageName,
        String mainClass,
        String destinationPath,
        Sdk sdk,
        Set<ModuleType> modules
) {}