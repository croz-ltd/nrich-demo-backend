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
