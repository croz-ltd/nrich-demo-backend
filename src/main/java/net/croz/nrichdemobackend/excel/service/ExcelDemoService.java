package net.croz.nrichdemobackend.excel.service;

public interface ExcelDemoService {

    byte[] createExcelReportFromFlatData();

    byte[] createExcelReportFromProvider();

}
