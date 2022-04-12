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
@SequenceGenerator(name = "bookSequenceGenerator", sequenceName = "book_seq")
@Entity
public class Book extends BaseEntity<Long> {

    @GeneratedValue(generator = "bookSequenceGenerator")
    @Id
    private Long id;

    @NotNull
    private String title;

    @Size(min = 5, max = 20)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookType bookType;

}
