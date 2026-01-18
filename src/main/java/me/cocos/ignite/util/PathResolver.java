package me.cocos.ignite.util;

import java.nio.file.Path;

public class PathResolver {

    public static Path resolveSourcePath(Path moduleRoot, String packageName) {
        return moduleRoot
                .resolve("src/main/java")
                .resolve(packageName.replace('.', '/'));
    }

    public static Path resolveResourcePath(Path moduleRoot) {
        return moduleRoot.resolve("src/main/resources");
    }

    public static Path resolveBuildFile(Path moduleRoot) {
        return moduleRoot.resolve("build.gradle");
    }
}
