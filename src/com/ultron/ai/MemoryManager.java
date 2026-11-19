
package com.ultron.ai;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MemoryManager {

    private static final Path MEMORY_FILE =
            Path.of("ultron-memory.txt");

    public void saveMemory(String information) {
        try {
            Files.writeString(
                MEMORY_FILE,
                information + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.out.println(
                "ULTRON: Unable to save memory: " + e.getMessage()
            );
        }
    }

    public String loadMemory() {
        try {
            if (Files.exists(MEMORY_FILE)) {
                return Files.readString(MEMORY_FILE);
            }
        } catch (IOException e) {
            System.out.println(
                "ULTRON: Unable to load memory: " + e.getMessage()
            );
        }

        return "";
    }
}
