package net.croz.nrichdemobackend.registry.model;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrichdemobackend.infrastructure.persistence.model.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Setter
@Getter
@Audited
@SequenceGenerator(name = "countrySequenceGenerator", sequenceName = "country_seq")
@Entity
public class Country extends BaseEntity<Long>  {

    @GeneratedValue(generator = "countrySequenceGenerator")
    @Id
    private Long id;

    private String name;

}
