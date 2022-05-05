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

package net.croz.nrichdemobackend.encrypt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EncryptDemoControllerTest extends BaseControllerTest {

    @Test
    void shouldEncryptAndDecryptArgument() throws Exception {
        // given
        String value = "confidential-value";

        // when
        ResultActions result = performGetRequest(fullUrl("encrypt-result"), Map.of("value", value));

        // then
        result.andExpect(status().isOk())
            .andExpect(content().string(not(value)));

        String encryptedValue = result.andReturn().getResponse().getContentAsString();

        // and when
        ResultActions decryptedResult = performGetRequest(fullUrl("decrypt-argument"), Map.of("value", encryptedValue));

        // then
        decryptedResult.andExpect(status().isOk())
            .andExpect(content().string(value));
    }

    @Test
    void shouldEncryptAndDecryptNestedArgument() throws Exception {
        // given
        String value = "confidential-value";
        String key = "nestedValue";

        // when
        ResultActions result = performPostRequest(fullUrl("encrypt-nested-result"), Map.of(key, value));

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$." + key).value(not(value)));

        Map<String, String> resultMap = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
        });

        // and when
        ResultActions decryptedResult = performPostRequest(fullUrl("decrypt-nested-argument"), resultMap);

        // then
        decryptedResult.andExpect(status().isOk())
            .andExpect(jsonPath("$." + key).value(value));
    }

    @Test
    void shouldEncryptAndDecryptWithOperationsDefinedInProperties() throws Exception {
        // given
        String value = "confidential-value";

        // when
        ResultActions result = performGetRequest(fullUrl("encrypt-result-defined-in-properties"), Map.of("value", value));

        // then
        result.andExpect(status().isOk())
            .andExpect(content().string(not(value)));

        String encryptedValue = result.andReturn().getResponse().getContentAsString();

        // and when
        ResultActions decryptedResult = performGetRequest(fullUrl("decrypt-argument-defined-in-properties"), Map.of("value", encryptedValue));

        // then
        decryptedResult.andExpect(status().isOk())
            .andExpect(content().string(value));
    }

    private String fullUrl(String path) {
        return String.format("/encrypt/%s", path);
    }
}
