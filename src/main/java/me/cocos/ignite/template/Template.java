package me.cocos.ignite.template;

import me.cocos.ignite.model.ProjectConfig;

public interface Template {

    String generate(ProjectConfig config);

}
