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

package net.croz.nrichdemobackend.webmvc.controller;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WebMvcDemoControllerTest extends BaseControllerTest {

    private static final String NOTIFICATION_TITLE_PATH = "$.notification.title";

    private static final String NOTIFICATION_CONTENT_PATH = "$.notification.content";

    private static final String NOTIFICATION_MESSAGE_LIST_PATH = "$.notification.messageList[0]";

    @Test
    void shouldReturnDefaultNotificationForGenericException() throws Exception {
        // given
        String requestUrl = fullUrl("generic-exception");

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(status().is5xxServerError())
            .andExpect(jsonPath(NOTIFICATION_TITLE_PATH).value("Error"))
            .andExpect(jsonPath(NOTIFICATION_CONTENT_PATH).value("Error occurred"));
    }

    @Test
    void shouldResolveConfiguredMessageForException() throws Exception {
        // given
        String requestUrl = fullUrl("configured-exception");

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(status().is4xxClientError())
            .andExpect(jsonPath(NOTIFICATION_TITLE_PATH).value("Title for configured exception"))
            .andExpect(jsonPath(NOTIFICATION_CONTENT_PATH).value("Content for configured exception"))
            .andExpect(jsonPath(NOTIFICATION_MESSAGE_LIST_PATH).value(startsWith("UUID:")));
    }

    @Test
    void shouldUnwrapExceptionToResolveConfiguredMessage() throws Exception {
        // given
        String requestUrl = fullUrl("unwrapped-configured-exception");

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(jsonPath(NOTIFICATION_TITLE_PATH).value("Title for configured exception"))
            .andExpect(jsonPath(NOTIFICATION_CONTENT_PATH).value("Content for configured exception"));
    }

    @Test
    void shouldResolveConfiguredMessageForExceptionWithArguments() throws Exception {
        // given
        String requestUrl = fullUrl("configured-exception-with-arguments");

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(status().is4xxClientError())
            .andExpect(jsonPath(NOTIFICATION_TITLE_PATH).value("Title for configured exception with arguments"))
            .andExpect(jsonPath(NOTIFICATION_CONTENT_PATH).value("Content for configured exception with arguments: first argument and second argument"));
    }

    @Test
    void shouldResolveConfiguredMessageForConstraintValidationException() throws Exception {
        // given
        String requestUrl = fullUrl("validation-failed-exception");

        // when
        ResultActions result = performPostRequest(requestUrl);

        // then
        result.andExpect(status().is4xxClientError())
            .andExpect(jsonPath(NOTIFICATION_TITLE_PATH).value("Validation failed"))
            .andExpect(jsonPath(NOTIFICATION_CONTENT_PATH).value("Found validation errors:"))
            .andExpect(jsonPath(NOTIFICATION_MESSAGE_LIST_PATH).value("name: Cannot be null"));
    }

    @Test
    void shouldNotThrowValidationFailedExceptionForValidRequest() throws Exception {
        // given
        String requestUrl = fullUrl("validation-failed-exception");

        // when
        ResultActions result = performPostRequest(requestUrl, Map.of("name", "name"));

        // then
        result.andExpect(status().isOk());
    }

    @Test
    void shouldResolveConfiguredMessageForBindValidationException() throws Exception {
        // given
        String requestUrl = fullUrl("bind-validation-failed-exception");

        // when
        ResultActions result = performFormPostRequest(requestUrl);

        // then
        result.andExpect(status().is4xxClientError())
            .andExpect(jsonPath(NOTIFICATION_TITLE_PATH).value("Validation failed"))
            .andExpect(jsonPath(NOTIFICATION_CONTENT_PATH).value("Found validation errors:"))
            .andExpect(jsonPath(NOTIFICATION_MESSAGE_LIST_PATH).value("name: Cannot be null"));
    }

    @Test
    void shouldNotThrowBindValidationFailedExceptionForValidRequest() throws Exception {
        // given
        String requestUrl = fullUrl("bind-validation-failed-exception");

        // when
        ResultActions result = performFormPostRequest(requestUrl, Map.of("name", "name"));

        // then
        result.andExpect(status().isOk());
    }

    @Test
    void shouldNotSerializeTransientProperties() throws Exception {
        // given
        String requestUrl = fullUrl("transient-properties-serialization");
        String value = "value";
        Map<String, String> request = Map.of("nonTransientValue", value, "transientValue", value);

        // when
        ResultActions result = performFormPostRequest(requestUrl, request);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.nonTransientValue").value(value))
            .andExpect(jsonPath("$.transientValue").doesNotExist());
    }

    @Test
    void shouldConvertEmptyStringsToNull() throws Exception {
        // given
        String requestUrl = fullUrl("empty-strings-to-null");
        Map<String, String> request = Map.of("nonEmptyString", "value", "emptyString", "");

        // when
        ResultActions result = performFormPostRequest(requestUrl, request);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.nonEmptyString").value("value"))
            .andExpect(jsonPath("$.emptyString").doesNotExist());
    }

    private String fullUrl(String path) {
        return String.format("/webmvc/%s", path);
    }
}
