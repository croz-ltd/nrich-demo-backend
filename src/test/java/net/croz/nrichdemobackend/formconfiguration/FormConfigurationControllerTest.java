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
