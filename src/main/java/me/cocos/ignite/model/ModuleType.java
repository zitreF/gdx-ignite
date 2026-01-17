package me.cocos.ignite.model;

public enum ModuleType {
    DESKTOP("lwjgl3"),
    ANDROID("android"),
    IOS("ios"),
    HTML("html");

    private final String directoryName;

    ModuleType(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}