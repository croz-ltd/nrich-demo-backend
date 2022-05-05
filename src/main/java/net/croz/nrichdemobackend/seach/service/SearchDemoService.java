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

import net.croz.nrichdemobackend.seach.model.Car;
import net.croz.nrichdemobackend.seach.result.SearchCarResult;
import net.croz.nrichdemobackend.seach.request.SearchCarDemoRequest;
import net.croz.nrichdemobackend.seach.request.StringSearchCarDemoRequest;
import org.springframework.data.domain.Page;

public interface SearchDemoService {

    Page<SearchCarResult> searchCar(SearchCarDemoRequest request);

    Page<Car> stringSearchCar(StringSearchCarDemoRequest request);
}
