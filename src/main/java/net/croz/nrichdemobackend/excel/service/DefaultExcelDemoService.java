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

package net.croz.nrichdemobackend.excel.service;

import lombok.RequiredArgsConstructor;
import net.croz.nrich.excel.api.model.MultiRowDataProvider;
import net.croz.nrich.excel.api.model.TemplateVariable;
import net.croz.nrich.excel.api.request.CreateExcelReportRequest;
import net.croz.nrich.excel.api.service.ExcelReportService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultExcelDemoService implements ExcelDemoService {

    private static final String TEMPLATE_PATH = "classpath:excel/template.xlsx";

    private static final int BATCH_SIZE = 15;

    private static final int FIRST_ROW_INDEX = 3;

    private final ExcelReportService excelReportService;

    private final MultiRowDataProvider excelDemoProvider;

    @Override
    public byte[] createExcelReportFromFlatData() {
        Object[][] flatData = new Object[][] {
            { false, BigDecimal.TEN, null, 50, "first row" },
            { true, BigDecimal.ONE, null, null, "second row" }
        };

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        CreateExcelReportRequest request = CreateExcelReportRequest.fromFlatData(flatData)
            .templateVariableList(List.of(new TemplateVariable("templateVariable", "Excel report flat data")))
            .outputStream(outputStream)
            .batchSize(flatData.length)
            .templatePath(TEMPLATE_PATH)
            .firstRowIndex(FIRST_ROW_INDEX).build();

        excelReportService.createExcelReport(request);

        return outputStream.toByteArray();
    }

    @Override
    public byte[] createExcelReportFromProvider() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        CreateExcelReportRequest request = CreateExcelReportRequest.fromRowDataProvider(excelDemoProvider)
            .templateVariableList(List.of(new TemplateVariable("templateVariable", "Excel report row data provider")))
            .batchSize(BATCH_SIZE)
            .outputStream(outputStream)
            .templatePath(TEMPLATE_PATH)
            .firstRowIndex(FIRST_ROW_INDEX).build();

        excelReportService.createExcelReport(request);

        return outputStream.toByteArray();
    }
}
