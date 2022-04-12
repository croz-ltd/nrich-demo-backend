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
