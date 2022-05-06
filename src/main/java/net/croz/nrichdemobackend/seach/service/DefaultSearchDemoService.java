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

package net.croz.nrichdemobackend.seach.service;

import lombok.RequiredArgsConstructor;
import net.croz.nrich.search.api.model.SearchConfiguration;
import net.croz.nrich.search.api.model.SearchJoin;
import net.croz.nrich.search.api.util.PageableUtil;
import net.croz.nrichdemobackend.seach.model.Car;
import net.croz.nrichdemobackend.seach.result.SearchCarResult;
import net.croz.nrichdemobackend.seach.repository.CarSearchDemoRepository;
import net.croz.nrichdemobackend.seach.request.SearchCarDemoRequest;
import net.croz.nrichdemobackend.seach.request.StringSearchCarDemoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DefaultSearchDemoService implements SearchDemoService {

    private final CarSearchDemoRepository carSearchDemoRepository;

    @Override
    public Page<SearchCarResult> searchCar(SearchCarDemoRequest request) {
        SearchConfiguration<Car, SearchCarResult, SearchCarDemoRequest> searchConfiguration = SearchConfiguration.<Car, SearchCarResult, SearchCarDemoRequest>builder()
            .resolvePropertyMappingUsingPrefix(true)
            .resultClass(SearchCarResult.class)
            .build();

        return carSearchDemoRepository.findAll(request, searchConfiguration, PageableUtil.convertToPageable(request));
    }

    @Override
    public Page<Car> stringSearchCar(StringSearchCarDemoRequest request) {
        SearchConfiguration<Car, Car, Map<String, Object>> searchConfiguration = SearchConfiguration.<Car, Car, Map<String, Object>>builder()
            .joinList(List.of(SearchJoin.innerJoinFetch("carType")))
            .resolvePropertyMappingUsingPrefix(true)
            .anyMatch(true)
            .build();

        return carSearchDemoRepository.findAll(request.getSearchTerm(), request.getPropertyToSearchList(), searchConfiguration, Pageable.unpaged());
    }
}
