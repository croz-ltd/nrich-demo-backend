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
