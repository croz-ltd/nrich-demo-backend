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

package net.croz.nrichdemobackend.registry.model;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrichdemobackend.infrastructure.persistence.model.BaseEntity;
import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Setter
@Getter
@Audited
@SequenceGenerator(name = "addressSequenceGenerator", sequenceName = "address_seq")
@Entity
public class Address extends BaseEntity<Long> {

    @GeneratedValue(generator = "addressSequenceGenerator")
    @Id
    private Long id;

    @Size(min = 2, max = 200)
    private String street;

    @Min(1)
    private Integer streetNumber;

    @Size(min = 2, max = 200)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
}
