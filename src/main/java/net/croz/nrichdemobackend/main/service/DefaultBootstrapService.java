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

package net.croz.nrichdemobackend.main.service;

import lombok.RequiredArgsConstructor;
import net.croz.nrichdemobackend.excel.model.ExcelDemoEntity;
import net.croz.nrichdemobackend.main.constants.BootstrapConstants;
import net.croz.nrichdemobackend.registry.model.Address;
import net.croz.nrichdemobackend.registry.model.Author;
import net.croz.nrichdemobackend.registry.model.AuthorBook;
import net.croz.nrichdemobackend.registry.model.AuthorBookId;
import net.croz.nrichdemobackend.registry.model.Book;
import net.croz.nrichdemobackend.registry.model.BookType;
import net.croz.nrichdemobackend.registry.model.Country;
import net.croz.nrichdemobackend.seach.model.Car;
import net.croz.nrichdemobackend.seach.model.CarType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class DefaultBootstrapService implements BootstrapService {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public void createBootstrapData() {
        createExcelBootstrapData();
        createRegistryBootstrapData();
        createSearchBootstrapData();
    }

    private void createExcelBootstrapData() {
        IntStream.range(0, BootstrapConstants.EXCEL_BOOTSTRAP_DATA_SIZE)
            .mapToObj(this::createExcelDemoEntity)
            .forEach(entityManager::persist);
    }

    private ExcelDemoEntity createExcelDemoEntity(int count) {
        ExcelDemoEntity entity = new ExcelDemoEntity();

        entity.setBooleanProperty(count % 2 == 0);
        entity.setDecimalProperty(BigDecimal.ONE.add(new BigDecimal(count)));
        entity.setInstantProperty(instantOf(BootstrapConstants.EXCEL_DEMO_ENTITY_DATE).plus(count, ChronoUnit.DAYS));
        entity.setIntegerProperty(count);
        entity.setStringProperty(String.format(BootstrapConstants.STRING_VALUE_FORMAT, count));

        return entity;
    }

    private void createRegistryBootstrapData() {
        createBookRegistryData();
        createAddressRegistryData();
    }

    private void createBookRegistryData() {
        List<BookType> bookTypeList = IntStream.range(0, BootstrapConstants.REGISTRY_SMALL_BOOSTRAP_DATA_SIZE)
            .mapToObj(this::createBookType)
            .collect(Collectors.toList());

        bookTypeList.forEach(entityManager::persist);

        List<Book> bookList = IntStream.range(0, BootstrapConstants.REGISTRY_BOOSTRAP_DATA_SIZE)
            .mapToObj(value -> createBook(value, resolveElement(bookTypeList, value)))
            .collect(Collectors.toList());

        bookList.forEach(entityManager::persist);

        List<Author> authorList = IntStream.range(0, BootstrapConstants.REGISTRY_BOOSTRAP_DATA_SIZE)
            .mapToObj(this::createAuthor)
            .collect(Collectors.toList());

        authorList.forEach(entityManager::persist);

        IntStream.range(0, authorList.size()).forEach(value -> {
            AuthorBook authorBook = createAuthorBook(authorList.get(value), bookList.get(value), value);

            entityManager.persist(authorBook);
        });
    }

    private BookType createBookType(int count) {
        BookType bookType = new BookType();

        bookType.setName(String.format(BootstrapConstants.BOOK_TYPE_FORMAT, count));

        return bookType;
    }

    private Book createBook(int count, BookType bookType) {
        Book book = new Book();

        book.setBookType(bookType);
        book.setIsbn(String.format(BootstrapConstants.ISBN_FORMAT, count));
        book.setTitle(String.format(BootstrapConstants.TITLE_FORMAT, count));
        book.setNew(count % 2 == 0);

        return book;
    }

    private Author createAuthor(int count) {
        Author author = new Author();

        author.setFirstName(String.format(BootstrapConstants.FIRST_NAME_FORMAT, count));
        author.setLastName(String.format(BootstrapConstants.LAST_NAME_FORMAT, count));
        author.setIsForeign(count % 2 == 0);

        return author;
    }

    private AuthorBook createAuthorBook(Author author, Book book, int count) {
        AuthorBookId authorBookId = new AuthorBookId();

        authorBookId.setAuthor(author);
        authorBookId.setBook(book);

        AuthorBook authorBook = new AuthorBook();

        authorBook.setId(authorBookId);
        authorBook.setEdition(String.format(BootstrapConstants.EDITION_FORMAT, count));
        authorBook.setEditionNumber(count);

        return authorBook;
    }

    private void createAddressRegistryData() {
        List<Country> countryList = IntStream.range(0, BootstrapConstants.REGISTRY_SMALL_BOOSTRAP_DATA_SIZE)
            .mapToObj(this::createCountry)
            .collect(Collectors.toList());

        countryList.forEach(entityManager::persist);

        List<Address> addressList = IntStream.range(0, BootstrapConstants.REGISTRY_BOOSTRAP_DATA_SIZE)
            .mapToObj(value -> createAddress(value, resolveElement(countryList, value)))
            .collect(Collectors.toList());

        addressList.forEach(entityManager::persist);
    }

    private Country createCountry(int count) {
        Country country = new Country();

        country.setName(String.format(BootstrapConstants.COUNTRY_NAME_FORMAT, count));

        return country;
    }

    private Address createAddress(int count, Country country) {
        Address address = new Address();

        address.setCountry(country);
        address.setCity(String.format(BootstrapConstants.CITY_NAME_FORMAT, count));
        address.setStreet(String.format(BootstrapConstants.STREET_NAME_FORMAT, count));
        address.setStreetNumber(count + 1);

        return address;
    }

    private void createSearchBootstrapData() {
        List<CarType> carTypeList = IntStream.range(0, BootstrapConstants.SEARCH_SMALL_BOOSTRAP_DATA_SIZE)
            .mapToObj(this::createCarType)
            .collect(Collectors.toList());

        carTypeList.forEach(entityManager::persist);

        List.of(
            createCar("AB-123", instantOf("2015-01-01"), BigDecimal.valueOf(10000), 0, carTypeList.get(0)),
            createCar("BC-456", instantOf("2016-01-01"), BigDecimal.valueOf(9000), 10000, carTypeList.get(1)),
            createCar("BC-457", instantOf("2016-01-02"), BigDecimal.valueOf(9400), 500, carTypeList.get(1)),
            createCar("AB-124", instantOf("2015-11-10"), BigDecimal.valueOf(17000), 500, carTypeList.get(0)),
            createCar("AB-124", instantOf("2015-11-10"), BigDecimal.valueOf(10000), 10, carTypeList.get(2)),
            createCar("DD-153", instantOf("2015-01-01"), BigDecimal.valueOf(15000), 20000, carTypeList.get(2)),
            createCar("DD-154", instantOf("2017-02-02"), BigDecimal.valueOf(10000.00), 3000, carTypeList.get(3))
        ).forEach(entityManager::persist);
    }

    private Car createCar(String registrationNumber, Instant manufacturedDate, BigDecimal price, Integer numberOfKilometers, CarType carType) {
        Car car = new Car();

        car.setRegistrationNumber(registrationNumber);
        car.setManufacturedTime(manufacturedDate);
        car.setPrice(price);
        car.setNumberOfKilometers(numberOfKilometers);
        car.setCarType(carType);

        return car;
    }

    private CarType createCarType(int count) {
        CarType carType = new CarType();

        carType.setMake(String.format(BootstrapConstants.CAR_MAKE_FORMAT, count));
        carType.setModel(String.format(BootstrapConstants.CAR_MODEL_FORMAT, count));

        return carType;
    }

    private static Instant instantOf(String formattedInstant) {
        return BootstrapConstants.DATE_TIME_FORMATTER.parse(formattedInstant, Instant::from);
    }

    private <T> T resolveElement(List<T> elementList, int count) {
        int index = (elementList.size() - 1) % (count == 0 ? 1 : count);

        return elementList.get(index);
    }
}
