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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@SequenceGenerator(name = "authorSequenceGenerator", sequenceName = "author_seq")
@Entity
public class Author extends BaseEntity<Long> {

    @GeneratedValue(generator = "authorSequenceGenerator")
    @Id
    private Long id;

    @Size(min = 1, max = 200)
    @NotNull
    private String firstName;

    @Size(min = 1, max = 200)
    @NotNull
    private String lastName;

    private Boolean isForeign = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author similarAuthor;

}
