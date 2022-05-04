package net.croz.nrichdemobackend.excel.controller;

import lombok.RequiredArgsConstructor;
import net.croz.nrichdemobackend.excel.service.ExcelDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("excel")
@RestController
public class ExcelDemoController {

    private final ExcelDemoService excelDemoService;

    @GetMapping("report-from-flat-data")
    public byte[] excelReportFromFlatData() {
        return excelDemoService.createExcelReportFromFlatData();
    }

    @GetMapping("report-from-provider")
    public byte[] excelReportFromProvider() {
        return excelDemoService.createExcelReportFromProvider();
    }
}
