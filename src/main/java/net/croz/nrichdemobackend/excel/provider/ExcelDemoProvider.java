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
