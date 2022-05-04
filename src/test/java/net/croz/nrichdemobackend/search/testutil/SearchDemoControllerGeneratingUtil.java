package net.croz.nrichdemobackend.search.testutil;

import java.util.HashMap;
import java.util.Map;

public final class SearchDemoControllerGeneratingUtil {

    private SearchDemoControllerGeneratingUtil() {
    }

    public static Map<String, Object> withDefaultPaging(Map<String, Object> parameters) {
        Map<String, Object> result = new HashMap<>(parameters);

        result.put("pageNumber", 0);
        result.put("pageSize", 100);

        return result;
    }
}
