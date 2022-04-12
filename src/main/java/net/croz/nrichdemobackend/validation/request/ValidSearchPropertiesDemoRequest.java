package net.croz.nrichdemobackend.validation.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.croz.nrich.validation.api.constraint.ValidSearchProperties;

@ToString
@RequiredArgsConstructor
@Getter
@ValidSearchProperties(propertyGroup = { @ValidSearchProperties.PropertyGroup({ "firstName", "lastName" }), @ValidSearchProperties.PropertyGroup("uniqueIdentifier") })
public class ValidSearchPropertiesDemoRequest {

    private final String firstName;

    private final String lastName;

    private final String uniqueIdentifier;

}
