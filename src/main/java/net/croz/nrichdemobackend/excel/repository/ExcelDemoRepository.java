package net.croz.nrichdemobackend.excel.repository;

import net.croz.nrichdemobackend.excel.model.ExcelDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelDemoRepository extends JpaRepository<ExcelDemoEntity, Long> {
}
