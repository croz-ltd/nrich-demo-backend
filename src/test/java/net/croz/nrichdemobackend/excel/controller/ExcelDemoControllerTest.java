package net.croz.nrichdemobackend.excel.controller;

import net.croz.nrichdemobackend.core.test.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static net.croz.nrichdemobackend.core.testutil.FileResolverUtil.readFileContent;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExcelDemoControllerTest extends BaseControllerTest {

    @Test
    void shouldExportExcelFromFlatData() throws Exception {
        // given
        byte[] expectedReport = readFileContent("excel/expected-report-from-flat-data.xlsx");
        String requestUrl = fullUrl("report-from-flat-data");

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(status().isOk())
            .andExpect(content().bytes(expectedReport));
    }

    @Test
    void shouldExportExcelFromProvider() throws Exception {
        // given
        byte[] expectedReport = readFileContent("excel/expected-report-from-provider.xlsx");
        String requestUrl = fullUrl("report-from-provider");

        // when
        ResultActions result = performGetRequest(requestUrl);

        // then
        result.andExpect(status().isOk())
            .andExpect(content().bytes(expectedReport));
    }

    private String fullUrl(String path) {
        return String.format("/excel/%s", path);
    }
}
