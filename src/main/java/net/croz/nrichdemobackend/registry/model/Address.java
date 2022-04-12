package net.croz.nrichdemobackend.registry.model;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrichdemobackend.infrastructure.persistence.model.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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
