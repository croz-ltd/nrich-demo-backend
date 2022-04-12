package net.croz.nrichdemobackend.registry.model;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrichdemobackend.infrastructure.persistence.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Setter
@Getter
@SequenceGenerator(name = "bookTypeSequenceGenerator", sequenceName = "book_type_seq")
@Entity
public class BookType extends BaseEntity<Long> {

    @GeneratedValue(generator = "bookTypeSequenceGenerator")
    @Id
    private Long id;

    private String name;

}
