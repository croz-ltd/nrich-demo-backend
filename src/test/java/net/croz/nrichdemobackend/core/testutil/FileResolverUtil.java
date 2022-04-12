package net.croz.nrichdemobackend.core.testutil;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;

public final class FileResolverUtil {

    private FileResolverUtil() {
    }

    @SneakyThrows
    public static String readFileContentAsString(String filePath) {
        return new String(readFileContent(filePath), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static byte[] readFileContent(String filePath) {
        return new ClassPathResource(filePath).getInputStream().readAllBytes();
    }
}
