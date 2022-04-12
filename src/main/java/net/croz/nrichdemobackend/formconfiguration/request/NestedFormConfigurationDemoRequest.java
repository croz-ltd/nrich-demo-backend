package net.croz.nrichdemobackend.formconfiguration.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class NestedFormConfigurationDemoRequest {

    @NotNull
    private String street;

    @Size(min = 2, max = 150)
    private String city;

    @Size(min = 2, max = 100)
    private String country;

}
