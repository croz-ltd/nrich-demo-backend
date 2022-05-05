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

import lombok.RequiredArgsConstructor;
import net.croz.nrich.notification.api.model.AdditionalNotificationData;
import net.croz.nrich.notification.api.model.NotificationSeverity;
import net.croz.nrich.notification.api.response.NotificationDataResponse;
import net.croz.nrich.notification.api.service.NotificationResponseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("notification")
@RestController
public class NotificationDemoController {

    private final NotificationResponseService notificationResponseService;

    private static final Map<String, Object> RESULT = Map.of("success", true);

    @GetMapping("notification")
    public NotificationDataResponse<Map<String, Object>> notificationResponse() {
        return notificationResponseService.responseWithNotification(RESULT, "notification.notification-with-manual-action-resolving");
    }

    @GetMapping("notification-with-additional-data")
    public NotificationDataResponse<Map<String, Object>> notificationResponseWithAdditionalData() {
        AdditionalNotificationData additionalNotificationData = AdditionalNotificationData.builder()
            .severity(NotificationSeverity.WARNING)
            .uxNotificationOptions(Map.of("allowClose", false))
            .build();

        return notificationResponseService.responseWithNotification(RESULT, "notification.notification-with-manual-action-resolving-and-additional-data", additionalNotificationData);
    }

    @GetMapping("notification-with-action-resolved-from-request")
    public NotificationDataResponse<Map<String, Object>> notificationResponseWithActionResolvedFromRequest() {
        return notificationResponseService.responseWithNotificationActionResolvedFromRequest(RESULT);
    }

    @GetMapping("notification-with-additional-data-and-action-resolved-from-request")
    public NotificationDataResponse<Map<String, Object>> notificationResponseWithAdditionalDataAndActionResolvedFromRequest() {
        AdditionalNotificationData additionalNotificationData = AdditionalNotificationData.builder()
            .severity(NotificationSeverity.WARNING)
            .uxNotificationOptions(Map.of("allowClose", true))
            .build();

        return notificationResponseService.responseWithNotificationActionResolvedFromRequest(RESULT, additionalNotificationData);
    }
}
