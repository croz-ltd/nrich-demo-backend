package net.croz.nrichdemobackend.excel.provider;

import lombok.RequiredArgsConstructor;
import net.croz.nrich.excel.api.model.MultiRowDataProvider;
import net.croz.nrichdemobackend.excel.model.ExcelDemoEntity;
import net.croz.nrichdemobackend.excel.repository.ExcelDemoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class ExcelDemoProvider implements MultiRowDataProvider {

    private final ExcelDemoRepository excelDemoRepository;

    @Override
    public Object[][] resolveMultiRowData(int start, int limit) {
        Pageable pageable = PageRequest.of(start / limit, limit, Sort.by("id"));
        Page<ExcelDemoEntity> exampleList = excelDemoRepository.findAll(pageable);

        return exampleList.getContent().stream()
            .map(value -> new Object[] { value.getBooleanProperty(), value.getDecimalProperty(), value.getInstantProperty(), value.getIntegerProperty(), value.getStringProperty() })
            .toArray(Object[][]::new);
    }
}
