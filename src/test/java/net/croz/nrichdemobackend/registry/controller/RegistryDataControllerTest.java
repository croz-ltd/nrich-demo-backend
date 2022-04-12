package net.croz.nrichdemobackend.registry.controller;

import com.jayway.jsonpath.JsonPath;
import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import net.croz.nrichdemobackend.registry.model.Author;
import net.croz.nrichdemobackend.registry.model.Book;
import net.croz.nrichdemobackend.registry.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;

import static net.croz.nrichdemobackend.registry.testutil.RegistryDataGeneratingUtil.deleteRegistryData;
import static net.croz.nrichdemobackend.registry.testutil.RegistryDataGeneratingUtil.registryDataRequest;
import static net.croz.nrichdemobackend.registry.testutil.RegistryDataGeneratingUtil.saveRegistryRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistryDataControllerTest extends BaseControllerTest {

    @Test
    void shouldListData() throws Exception {
        // given
        String requestUrl = fullUrl("list");
        Map<String, Object> request = registryDataRequest(Author.class, "First_12", List.of("firstName"));

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().isOk()).andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void shouldListBulkData() throws Exception {
        // given
        Class<?> authorRegistryType = Author.class;
        Class<?> bookRegistryType = Book.class;
        String requestUrl = fullUrl("list-bulk");
        Map<String, Object> request = Map.of("registryRequestList", List.of(registryDataRequest(authorRegistryType), registryDataRequest(bookRegistryType)));

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.['" + bookRegistryType.getName() + "'].content").isNotEmpty())
            .andExpect(jsonPath("$.['" + authorRegistryType.getName() + "'].content").isNotEmpty());
    }

    @Test
    void shouldCreateUpdateAndDeleteRegistryEntity() throws Exception {
        // given
        Class<?> registryType = Country.class;
        String createUrl = fullUrl("create");
        String updateUrl = fullUrl("update");
        String deleteUrl = fullUrl("delete");

        String createCountryName = "someCountryNameForTest";
        String updateCountryName = "updatedCountryNameForTest";

        // when
        ResultActions createResult = performPostRequest(createUrl, saveRegistryRequest(registryType, objectMapper.writeValueAsString(Map.of("name", createCountryName))));

        // then
        createResult.andExpect(status().isOk());
        verifyCountryByNameTotalElements(createCountryName, 1);

        Integer id = JsonPath.read(createResult.andReturn().getResponse().getContentAsString(), "$.id");

        // and when
        ResultActions updateResult = performPostRequest(updateUrl, saveRegistryRequest(id, registryType, objectMapper.writeValueAsString(Map.of("name", updateCountryName))));

        // then
        updateResult.andExpect(status().isOk());
        verifyCountryByNameTotalElements(updateCountryName, 1);

        // and when
        ResultActions deleteResult = performPostRequest(deleteUrl, deleteRegistryData(id, registryType));

        // then
        deleteResult.andExpect(status().isOk());
        verifyCountryByNameTotalElements(updateCountryName, 0);
    }

    private void verifyCountryByNameTotalElements(String name, int expectedElements) throws Exception {
        String requestUrl = fullUrl("list");
        Map<String, Object> request = registryDataRequest(Country.class, name, List.of("name"));

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements").value(expectedElements));
    }

    private String fullUrl(String path) {
        return String.format("/nrich/registry/data/%s", path);
    }
}
