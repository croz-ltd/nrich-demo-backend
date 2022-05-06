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

package net.croz.nrichdemobackend.validation.controller;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import net.croz.nrichdemobackend.validation.request.NotNullWhenDemoRequest;
import net.croz.nrichdemobackend.validation.request.NullWhenDemoRequest;
import net.croz.nrichdemobackend.validation.request.ValidRangeDemoRequest;
import net.croz.nrichdemobackend.validation.request.ValidSearchPropertiesDemoRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ValidationDemoControllerTest extends BaseControllerTest {

    @CsvSource({ "valid,200", "invalid,400" })
    @ParameterizedTest
    void shouldValidateUsingMaxSizeInBytesConstraint(String value, int status) throws Exception {
        // given
        String requestUrl = fullUrl("max-size-in-bytes");
        Map<String, String> requestParam = Map.of("value", value);

        // when
        ResultActions result = performGetRequest(requestUrl, requestParam);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "first,200", "third,400" })
    @ParameterizedTest
    void shouldValidateUsingInListConstraint(String value, int status) throws Exception {
        // given
        String requestUrl = fullUrl("in-list");
        Map<String, String> requestParam = Map.of("value", value);

        // when
        ResultActions result = performGetRequest(requestUrl, requestParam);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "30040255732,200", "invalid,400" })
    @ParameterizedTest
    void shouldValidateUsingOibConstraint(String value, int status) throws Exception {
        // given
        String requestUrl = fullUrl("oib");
        Map<String, String> requestParam = Map.of("value", value);

        // when
        ResultActions result = performGetRequest(requestUrl, requestParam);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ ",,200", ",Avenue A,400" })
    @ParameterizedTest
    void shouldValidateUsingNotNullWhenConstraint(String city, String street, int status) throws Exception {
        // given
        String requestUrl = fullUrl("not-null-when");
        NotNullWhenDemoRequest request = new NotNullWhenDemoRequest(city, street);

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "100,OTHER,200", "100,COMPANY_CREDIT_CARD,400" })
    @ParameterizedTest
    void shouldValidateUsingNullWhenConstraint(BigDecimal amountForRefund, NullWhenDemoRequest.PaymentType paymentType, int status) throws Exception {
        // given
        String requestUrl = fullUrl("null-when");
        NullWhenDemoRequest request = new NullWhenDemoRequest(amountForRefund, paymentType);

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "file1.txt,text/plain,200", "file1.exe,text/plain,400", "file1.txt,application/json,400" })
    @ParameterizedTest
    void shouldValidateUsingValidFileConstraint(String filename, String contentType, int status) throws Exception {
        // given
        String requestUrl = fullUrl("valid-file");
        MockMultipartFile file = new MockMultipartFile("file", filename, contentType, "content".getBytes(StandardCharsets.UTF_8));

        // when
        ResultActions result = performMultipartRequest(requestUrl, file);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "file1.doc,application/msword,200", "file1.exe,application/msword,400", "file1.doc,application/json,400" })
    @ParameterizedTest
    void shouldValidateUsingValidFileResolvableConstraint(String filename, String contentType, int status) throws Exception {
        // given
        String requestUrl = fullUrl("valid-file-resolvable");
        MockMultipartFile file = new MockMultipartFile("file", filename, contentType, "content".getBytes(StandardCharsets.UTF_8));

        // when
        ResultActions result = performMultipartRequest(requestUrl, file);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "50,51,200", "51,50,400", "51,51,400" })
    @ParameterizedTest
    void shouldValidateUsingValidRangeConstraint(Integer ageFrom, Integer ageTo, int status) throws Exception {
        // given
        String requestUrl = fullUrl("valid-range");
        ValidRangeDemoRequest request = new ValidRangeDemoRequest(ageFrom, ageTo);

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().is(status));
    }

    @CsvSource({ "first,last,,200", ",,identifier,200", ",,,400", "first,,,400", ",last,,400" })
    @ParameterizedTest
    void shouldValidateUsingValidSearchProperties(String firstName, String lastName, String uniqueIdentifier, int status) throws Exception {
        // given
        String requestUrl = fullUrl("valid-search-properties");
        ValidSearchPropertiesDemoRequest request = new ValidSearchPropertiesDemoRequest(firstName, lastName, uniqueIdentifier);

        // when
        ResultActions result = performPostRequest(requestUrl, request);

        // then
        result.andExpect(status().is(status));
    }

    private String fullUrl(String path) {
        return String.format("/validation/%s", path);
    }
}
