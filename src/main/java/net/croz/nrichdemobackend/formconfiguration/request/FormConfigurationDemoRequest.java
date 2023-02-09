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

package net.croz.nrichdemobackend.formconfiguration.request;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrich.validation.api.constraint.InList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class FormConfigurationDemoRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @Size(min = 3, max = 3)
    private String phonePrefix;

    @Size(min = 5, max = 9)
    private String phone;

    @Max(23)
    @Min(0)
    private Integer hours;

    @InList({ "mr", "mrs", "miss" })
    private String title;

    @NotBlank
    private String firstName;

    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal income;

    @Valid
    @NotNull
    private NestedFormConfigurationDemoRequest nestedFormConfigurationDemoRequest;

}
