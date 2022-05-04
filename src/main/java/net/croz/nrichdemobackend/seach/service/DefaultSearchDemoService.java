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
