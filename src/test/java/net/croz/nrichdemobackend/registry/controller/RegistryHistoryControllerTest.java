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

import com.jayway.jsonpath.JsonPath;
import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import net.croz.nrichdemobackend.registry.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;
import java.util.UUID;

import static net.croz.nrichdemobackend.registry.testutil.RegistryDataGeneratingUtil.registryHistoryRequest;
import static net.croz.nrichdemobackend.registry.testutil.RegistryDataGeneratingUtil.saveRegistryRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistryHistoryControllerTest extends BaseControllerTest {

    @Test
    void shouldListHistory() throws Exception {
        // given
        Class<?> registryType = Country.class;
        String requestUrl = "/nrich/registry/history/list";
        Integer id = createRegistryEntity(registryType);
        Map<String, Object> request = registryHistoryRequest(id, registryType);

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements").value(1));
    }

    private Integer createRegistryEntity(Class<?> registryType) throws Exception {
        String requestUrl = "/nrich/registry/data/create";

        ResultActions result = performPostRequest(requestUrl, saveRegistryRequest(registryType, objectMapper.writeValueAsString(Map.of("name", UUID.randomUUID()))));

        return JsonPath.read(result.andReturn().getResponse().getContentAsString(), "$.id");
    }
}
