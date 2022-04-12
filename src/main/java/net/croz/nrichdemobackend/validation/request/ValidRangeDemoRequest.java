package net.croz.nrichdemobackend.validation.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.croz.nrich.validation.api.constraint.ValidRange;

@ToString
@RequiredArgsConstructor
@Getter
@ValidRange(fromPropertyName = "ageFrom", toPropertyName = "ageTo")
public class ValidRangeDemoRequest {

    private final Integer ageFrom;

    private final Integer ageTo;

}
