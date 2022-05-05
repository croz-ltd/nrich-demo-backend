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

package net.croz.nrichdemobackend.seach.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.croz.nrich.search.api.annotation.Projection;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.Instant;

@RequiredArgsConstructor
@Getter
public class SearchCarResult {

    private final String registrationNumber;

    private final Instant manufacturedTime;

    private final BigDecimal price;

    private final Integer numberOfKilometers;

    @Projection(path = "carType.make")
    private final String carTypeMake;

    @Value("carType.model")
    private final String carTypeModel;
}
