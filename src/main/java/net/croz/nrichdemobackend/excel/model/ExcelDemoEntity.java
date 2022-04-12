package net.croz.nrichdemobackend.excel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@SequenceGenerator(name = "excelDemoEntitySequenceGenerator", sequenceName = "excel_demo_entity_seq")
@Entity
public class ExcelDemoEntity {

    @GeneratedValue(generator = "excelDemoEntitySequenceGenerator")
    @Id
    private Long id;

    private String stringProperty;

    private Boolean booleanProperty;

    private Instant instantProperty;

    private BigDecimal decimalProperty;

    private Integer integerProperty;

}
