package net.croz.nrichdemobackend.seach.service;

import net.croz.nrichdemobackend.seach.model.Car;
import net.croz.nrichdemobackend.seach.result.SearchCarResult;
import net.croz.nrichdemobackend.seach.request.SearchCarDemoRequest;
import net.croz.nrichdemobackend.seach.request.StringSearchCarDemoRequest;
import org.springframework.data.domain.Page;

public interface SearchDemoService {

    Page<SearchCarResult> searchCar(SearchCarDemoRequest request);

    Page<Car> stringSearchCar(StringSearchCarDemoRequest request);
}
