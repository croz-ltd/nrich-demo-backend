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

package net.croz.nrichdemobackend.search.controller;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static net.croz.nrichdemobackend.search.testutil.SearchDemoControllerGeneratingUtil.withDefaultPaging;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SearchDemoControllerTest extends BaseControllerTest {

    @MethodSource("shouldSearchCarMethodSource")
    @ParameterizedTest
    void shouldSearchCar(Map<String, Object> request, Integer count) throws Exception {
        // given
        String requestUrl = fullUrl("search-car");
        Map<String, Object> requestWithPaging = withDefaultPaging(request);

        // when
        ResultActions result = performPostRequest(requestUrl, requestWithPaging);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements").value(count));
    }

    private static Stream<Arguments> shouldSearchCarMethodSource() {
        return Stream.of(
            arguments(Map.of("registrationNumber", "AB-123"), 1),
            arguments(Map.of("registrationNumber", "AB-"), 3),
            arguments(Map.of("manufacturedTimeFrom", "2015-12-31T00:00:00Z"), 3),
            arguments(Map.of("manufacturedTimeFrom", "2015-12-31T00:00:00Z", "manufacturedTimeTo", "2016-01-02T00:00:00Z"), 1),
            arguments(Map.of("priceFromIncluding", 10000, "priceTo", 16000), 4),
            arguments(Map.of("numberOfKilometers", 3000), 1),
            arguments(Map.of("carTypeMake", "Car make: 1"), 2),
            arguments(Map.of("carTypeModel", "Car model: 2"), 2),
            arguments(Map.of("carTypeMake", "Car make: 1", "carTypeModel", "Car model: 2"), 0)
        );
    }

    @MethodSource("shouldStringSearchCarMethodSource")
    @ParameterizedTest
    void shouldStringSearchCar(List<String> propertyToSearchList, String searchTerm, Integer count) throws Exception {
        // given
        String requestUrl = fullUrl("string-search-car");
        Map<String, Object> request = Map.of("searchTerm", searchTerm, "propertyToSearchList", propertyToSearchList);
        Map<String, Object> requestWithPaging = withDefaultPaging(request);

        // when
        ResultActions result = performPostRequest(requestUrl, requestWithPaging);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.totalElements").value(count));
    }

    private static Stream<Arguments> shouldStringSearchCarMethodSource() {
        return Stream.of(
            arguments(List.of("registrationNumber"), "AB-", 3),
            arguments(List.of("numberOfKilometers"), "3000", 1),
            arguments(List.of("carType.make"), "Car make: 2", 2),
            arguments(List.of("carType.model"), "Car model: 2", 2)
        );
    }

    private String fullUrl(String path) {
        return String.format("/search/%s", path);
    }
}
