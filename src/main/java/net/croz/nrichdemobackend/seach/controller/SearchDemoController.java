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
