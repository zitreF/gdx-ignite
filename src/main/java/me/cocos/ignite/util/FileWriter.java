package me.cocos.ignite.util;

import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.nio.file.Path;

public class FileWriter {

    public static void write(Path path, String content) throws IOException {
        VirtualFile parentDir = VfsUtil.createDirectoryIfMissing(path.getParent().toString());
        if (parentDir == null) {
            throw new IOException("Could not create directory: " + path.getParent());
        }

        VirtualFile file = parentDir.findOrCreateChildData(FileWriter.class, path.getFileName().toString());
        VfsUtil.saveText(file, content);
    }

    public static void createDirectory(Path path) throws IOException {
        VfsUtil.createDirectoryIfMissing(path.toString());
    }
}
