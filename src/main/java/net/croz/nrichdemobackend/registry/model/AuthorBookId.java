package net.croz.nrichdemobackend.registry.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Embeddable
public class AuthorBookId implements Serializable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @EqualsAndHashCode.Include
    public Long getAuthorId() {
        return author.getId();
    }

    @EqualsAndHashCode.Include
    public Long getBookId() {
        return book.getId();
    }
}
