/*
 *  Copyright 2022 CROZ d.o.o, the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

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
