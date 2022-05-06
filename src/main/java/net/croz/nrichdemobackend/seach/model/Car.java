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

package net.croz.nrichdemobackend.seach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@SequenceGenerator(name = "carSequenceGenerator", sequenceName = "car_seq")
@Entity
public class Car {

    @GeneratedValue(generator = "carSequenceGenerator")
    @Id
    private Long id;

    private String registrationNumber;

    private Instant manufacturedTime;

    private BigDecimal price;

    private Integer numberOfKilometers;

    @ManyToOne(fetch = FetchType.LAZY)
    private CarType carType;
}
