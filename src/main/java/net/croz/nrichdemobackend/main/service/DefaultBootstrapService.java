package net.croz.nrichdemobackend.main.service;

import lombok.RequiredArgsConstructor;
import net.croz.nrichdemobackend.excel.model.ExcelDemoEntity;
import net.croz.nrichdemobackend.main.constants.BootstrapConstants;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class DefaultBootstrapService implements BootstrapService {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public void createBootstrapData() {
        createExcelBootstrapData();
    }

    private void createExcelBootstrapData() {
        IntStream.range(0, BootstrapConstants.EXCEL_BOOTSTRAP_DATA_SIZE)
            .mapToObj(this::createExcelDemoEntity)
            .forEach(entityManager::persist);
    }

    private ExcelDemoEntity createExcelDemoEntity(int count) {
        ExcelDemoEntity entity = new ExcelDemoEntity();

        entity.setBooleanProperty(count % 2 == 0);
        entity.setDecimalProperty(BigDecimal.ONE.add(new BigDecimal(count)));
        entity.setInstantProperty(instantOf(BootstrapConstants.EXCEL_DEMO_ENTITY_DATE).plus(count, ChronoUnit.DAYS));
        entity.setIntegerProperty(count);
        entity.setStringProperty(String.format(BootstrapConstants.STRING_VALUE_FORMAT, count));

        return entity;
    }

    private static Instant instantOf(String formattedInstant) {
        return BootstrapConstants.DATE_TIME_FORMATTER.parse(formattedInstant, Instant::from);
    }
}
