package net.croz.nrichdemobackend.seach.request;

import lombok.Getter;
import lombok.Setter;
import net.croz.nrich.search.api.request.BaseSortablePageableRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class SearchCarDemoRequest extends BaseSortablePageableRequest {

    private String registrationNumber;

    private Instant manufacturedTimeFrom;

    private Instant manufacturedTimeTo;

    @DecimalMin("0.00")
    private BigDecimal priceFromIncluding;

    @DecimalMin("0.00")
    private BigDecimal priceTo;

    @Min(0)
    private Integer numberOfKilometers;

    @Size(max = 20)
    private String carTypeMake;

    @Size(max = 40)
    private String carTypeModel;

}
