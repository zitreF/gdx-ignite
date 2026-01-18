package me.cocos.ignite.template;

import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.template.gradle.*;
import me.cocos.ignite.template.java.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

    private static final Map<Class<? extends Template>, Template> TEMPLATES = new HashMap<>();

    static {
        // GRADLE
        TEMPLATES.put(CoreBuildGradleTemplate.class, new CoreBuildGradleTemplate());
        TEMPLATES.put(Lwjgl3BuildGradleTemplate.class, new Lwjgl3BuildGradleTemplate());
        TEMPLATES.put(RootBuildGradleTemplate.class, new RootBuildGradleTemplate());
        TEMPLATES.put(SettingsGradleTemplate.class, new SettingsGradleTemplate());

        // JAVA
        TEMPLATES.put(CoreGameTemplate.class, new CoreGameTemplate());
        TEMPLATES.put(Lwjgl3LauncherTemplate.class, new Lwjgl3LauncherTemplate());
    }

    public static Template getTemplate(Class<? extends Template> type) {
        return TEMPLATES.computeIfAbsent(type, key -> {
            try {
                return key.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("Failed to instantiate template: " + key.getName(), e);
            }
        });
    }

    public static String generate(Class<? extends Template> type, ProjectConfig config) {
        return getTemplate(type).generate(config);
    }
}
