package net.croz.nrichdemobackend.registry.model;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrichdemobackend.infrastructure.persistence.model.BaseEntity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class AuthorBook extends BaseEntity<AuthorBookId> {

    @EmbeddedId
    private AuthorBookId id;

    @NotNull
    private String edition;

    @NotNull
    private Integer editionNumber;

}
