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
