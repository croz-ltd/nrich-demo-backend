package net.croz.nrichdemobackend.seach.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.croz.nrich.search.api.annotation.Projection;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.Instant;

@RequiredArgsConstructor
@Getter
public class SearchCarResult {

    private final String registrationNumber;

    private final Instant manufacturedTime;

    private final BigDecimal price;

    private final Integer numberOfKilometers;

    @Projection(path = "carType.make")
    private final String carTypeMake;

    @Value("carType.model")
    private final String carTypeModel;
}
