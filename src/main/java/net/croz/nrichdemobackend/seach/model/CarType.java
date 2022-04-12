package net.croz.nrichdemobackend.seach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

@Setter
@Getter
@SequenceGenerator(name = "carTypeSequenceGenerator", sequenceName = "car_type_seq")
@Entity
public class CarType {

    @GeneratedValue(generator = "carTypeSequenceGenerator")
    @Id
    private Long id;

    @Size(max = 50)
    private String make;

    @Size(max = 100)
    private String model;

}
