package net.croz.nrichdemobackend.seach.controller;

import lombok.RequiredArgsConstructor;
import net.croz.nrichdemobackend.seach.model.Car;
import net.croz.nrichdemobackend.seach.result.SearchCarResult;
import net.croz.nrichdemobackend.seach.request.SearchCarDemoRequest;
import net.croz.nrichdemobackend.seach.request.StringSearchCarDemoRequest;
import net.croz.nrichdemobackend.seach.service.SearchDemoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("search")
@RestController
public class SearchDemoController {

    private final SearchDemoService searchDemoService;

    @PostMapping("search-car")
    public Page<SearchCarResult> searchCar(@Valid @RequestBody SearchCarDemoRequest request) {
        return searchDemoService.searchCar(request);
    }

    @PostMapping("string-search-car")
    public Page<Car> stringSearchCar(@Valid @RequestBody StringSearchCarDemoRequest request) {
        return searchDemoService.stringSearchCar(request);
    }

}
