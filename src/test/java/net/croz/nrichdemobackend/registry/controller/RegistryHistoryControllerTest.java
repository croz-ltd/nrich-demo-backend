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
