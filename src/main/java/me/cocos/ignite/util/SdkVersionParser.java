package me.cocos.ignite.util;

import com.intellij.openapi.projectRoots.Sdk;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SdkVersionParser {

    private static final Pattern JDK_VERSION_PATTERN = Pattern.compile("(\\d+)(?:\\.(\\d+))?");
    private static final Pattern GRADLE_VERSION_PATTERN = Pattern.compile("(\\d+\\.\\d+)");

    private SdkVersionParser() {}

    public static String getJavaVersion(Sdk sdk) {
        String version = sdk.getSdkType().getVersionString(sdk);
        if (version == null || version.isEmpty()) {
            return "21";
        }

        Matcher matcher = JDK_VERSION_PATTERN.matcher(version);
        if (matcher.find()) {
            String major = matcher.group(1);
            String minor = matcher.group(2);

            return major.equals("1") && minor != null ? minor : major;
        }

        return "21";
    }

    public static String getGradleVersion(String rawVersion) {
        if (rawVersion == null || rawVersion.isEmpty()) {
            return "8.5";
        }

        Matcher matcher = GRADLE_VERSION_PATTERN.matcher(rawVersion);
        return matcher.find() ? matcher.group(1) : "8.5";
    }
}
