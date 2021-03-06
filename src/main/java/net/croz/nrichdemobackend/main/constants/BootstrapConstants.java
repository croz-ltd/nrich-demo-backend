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

package net.croz.nrichdemobackend.main.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public final class BootstrapConstants {

    public static final int EXCEL_BOOTSTRAP_DATA_SIZE = 50;

    public static final String EXCEL_DEMO_ENTITY_DATE = "2022-03-01";

    public static final int SEARCH_SMALL_BOOSTRAP_DATA_SIZE = 5;

    public static final int REGISTRY_SMALL_BOOSTRAP_DATA_SIZE = 5;

    public static final int REGISTRY_BOOSTRAP_DATA_SIZE = 15;

    public static final String STRING_VALUE_FORMAT = "Value = %s";

    public static final String CAR_MAKE_FORMAT = "Car make: %s";

    public static final String CAR_MODEL_FORMAT = "Car model: %s";

    public static final String BOOK_TYPE_FORMAT = "Book type: %s";

    public static final String ISBN_FORMAT = "Isbn: %s";

    public static final String TITLE_FORMAT = "Title: %s";

    public static final String FIRST_NAME_FORMAT = "First_%s";

    public static final String LAST_NAME_FORMAT = "Last_%s";

    public static final String EDITION_FORMAT = "Edition: %s";

    public static final String COUNTRY_NAME_FORMAT = "Country: %s";

    public static final String CITY_NAME_FORMAT = "City: %s";

    public static final String STREET_NAME_FORMAT = "Street: %s";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd")
        .parseDefaulting(ChronoField.SECOND_OF_DAY, 0)
        .toFormatter()
        .withZone(ZoneId.of("UTC"));

    private BootstrapConstants() {
    }
}
