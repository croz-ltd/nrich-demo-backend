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

package net.croz.nrichdemobackend.seach.request;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrich.search.api.request.BaseSortablePageableRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class SearchCarDemoRequest extends BaseSortablePageableRequest {

    private String registrationNumber;

    private Instant manufacturedTimeFrom;

    private Instant manufacturedTimeTo;

    @DecimalMin("0.00")
    private BigDecimal priceFromIncluding;

    @DecimalMin("0.00")
    private BigDecimal priceTo;

    @Min(0)
    private Integer numberOfKilometers;

    @Size(max = 20)
    private String carTypeMake;

    @Size(max = 40)
    private String carTypeModel;

}
