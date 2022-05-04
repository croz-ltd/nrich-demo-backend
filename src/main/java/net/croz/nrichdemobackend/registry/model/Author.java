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
