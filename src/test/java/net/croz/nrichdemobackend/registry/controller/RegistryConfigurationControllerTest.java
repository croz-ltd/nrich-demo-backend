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

package net.croz.nrichdemobackend.registry.controller;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import net.croz.nrichdemobackend.core.testutil.FileResolverUtil;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistryConfigurationControllerTest extends BaseControllerTest {

    @Test
    void shouldFetchRegistryConfiguration() throws Exception {
        // given
        String jsonContent = FileResolverUtil.readFileContentAsString("registry/expected-registry-configuration.json");
        String requestUrl = "/nrich/registry/configuration/fetch";

        // when
        ResultActions result = performPostRequest(requestUrl);

        // then
        result.andExpect(status().isOk())
            .andExpect(content().json(jsonContent));
    }
}
