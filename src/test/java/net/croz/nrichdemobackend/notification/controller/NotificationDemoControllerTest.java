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

package net.croz.nrichdemobackend.notification.controller;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NotificationDemoControllerTest extends BaseControllerTest {

    @MethodSource("shouldResolveNotificationMethodSource")
    @ParameterizedTest
    void shouldResolveNotification(String path, String title, String content) throws Exception {
        // given
        String requestUrl = fullUrl(path);

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.notification.title").value(title))
            .andExpect(jsonPath("$.notification.content").value(content));
    }

    private static Stream<Arguments> shouldResolveNotificationMethodSource() {
        return Stream.of(
            arguments("notification", "Action with manual resolving", "Action with manual resolving was successful."),
            arguments("notification-with-additional-data", "Action with manual resolving and additional data", "Action with manual resolving and additional data was successful with warnings."),
            arguments("notification-with-action-resolved-from-request", "Action with notification resolved from request", "Action with notification resolved from request was successful."),
            arguments(
                "notification-with-additional-data-and-action-resolved-from-request", "Action with notification resolved from request and additional data",
                "Action with notification resolved from request and additional data was successful with warnings."
            )

        );
    }

    private String fullUrl(String path) {
        return String.format("/notification/%s", path);
    }
}
