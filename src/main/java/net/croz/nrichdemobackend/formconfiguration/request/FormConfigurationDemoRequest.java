package net.croz.nrichdemobackend.formconfiguration.request;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrich.validation.api.constraint.InList;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class FormConfigurationDemoRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @Size(min = 3, max = 3)
    private String phonePrefix;

    @Size(min = 5, max = 9)
    private String phone;

    @Max(23)
    @Min(0)
    private Integer hours;

    @InList({ "mr", "mrs", "miss" })
    private String title;

    @NotBlank
    private String firstName;

    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal income;

    @Valid
    @NotNull
    private NestedFormConfigurationDemoRequest nestedFormConfigurationDemoRequest;

}
