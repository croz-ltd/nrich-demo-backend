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

package net.croz.nrichdemobackend.formconfiguration;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import net.croz.nrichdemobackend.registry.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;

import static net.croz.nrichdemobackend.core.testutil.FileResolverUtil.readFileContentAsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FormConfigurationControllerTest extends BaseControllerTest {

    private static final String FORM_CONFIGURATION_CONTROLLER_URL = "/nrich/form/configuration/fetch";

    private static final String REGISTRY_CREATE_FORMAT = "%s:::create";

    private static final String REGISTRY_UPDATE_FORMAT = "%s:::update";

    @Test
    void shouldResolveFormConfiguration() throws Exception {
        // given
        String jsonContent = readFileContentAsString("form-configuration/expected-form-configuration.json");
        List<String> formIdList = List.of("form-configuration.demo-request");

        // when
        ResultActions result = performPostRequest(FORM_CONFIGURATION_CONTROLLER_URL, Map.of("formIdList", formIdList));

        // then
        result.andExpect(status().isOk())
            .andExpect(content().json(jsonContent));
    }

    // since registry is also configured then registry entities are available for form configuration
    @Test
    void shouldResolveFormConfigurationForRegistryEntities() throws Exception {
        String registryId = Author.class.getName();
        List<String> formIdList = List.of(String.format(REGISTRY_CREATE_FORMAT, registryId), String.format(REGISTRY_UPDATE_FORMAT, registryId));

        // when
        ResultActions result = performPostRequest(FORM_CONFIGURATION_CONTROLLER_URL, Map.of("formIdList", formIdList));

        // then
        result.andExpect(status().isOk());
    }
}
