package net.croz.nrichdemobackend.registry.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// example of using a custom validation when creating a country
@Setter
@Getter
public class CountryCreateRequest {

    @Size(min = 2, max = 500)
    @NotNull
    private String name;

}
