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

package net.croz.nrichdemobackend.registry.testutil;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class RegistryDataGeneratingUtil {

    private static final String REGISTRY_TYPE_KEY = "classFullName";

    private static final String ID = "id";

    private static final String PAGE_NUMBER = "pageNumber";

    private static final String PAGE_SIZE = "pageSize";

    private static final String ENTITY_DATA = "jsonEntityData";

    private RegistryDataGeneratingUtil() {
    }

    public static Map<String, Object> registryDataRequest(Class<?> registryType) {
        return registryDataRequest(registryType, null, Collections.emptyList());
    }

    public static Map<String, Object> registryDataRequest(Class<?> registryType, String query, List<String> propertyNameList) {
        Map<String, Object> searchParameter = Collections.emptyMap();

        if (StringUtils.hasText(query) && !CollectionUtils.isEmpty(propertyNameList)) {
            searchParameter = Map.of("query", query, "propertyNameList", propertyNameList);
        }

        return Map.of(REGISTRY_TYPE_KEY, registryType, PAGE_NUMBER, 0, PAGE_SIZE, 100, "searchParameter", searchParameter);
    }

    public static Map<String, Object> saveRegistryRequest(Class<?> registryType, String saveData) {
        return saveRegistryRequest(null, registryType, saveData);
    }

    public static Map<String, Object> saveRegistryRequest(Object id, Class<?> registryType, String saveData) {
        if (id == null) {
            return Map.of(REGISTRY_TYPE_KEY, registryType, ENTITY_DATA, saveData);
        }

        return Map.of(ID, id, REGISTRY_TYPE_KEY, registryType, ENTITY_DATA, saveData);
    }

    public static Map<String, Object> deleteRegistryData(Object id, Class<?> registryType) {
        return Map.of(ID, id, REGISTRY_TYPE_KEY, registryType);
    }

    public static Map<String, Object> registryHistoryRequest(Object id, Class<?> registryType) {
        return Map.of(REGISTRY_TYPE_KEY, registryType, "registryRecordId", id, PAGE_NUMBER, 0, PAGE_SIZE, 100);
    }
}
