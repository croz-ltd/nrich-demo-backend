package net.croz.nrichdemobackend.validation.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.croz.nrich.validation.api.constraint.NotNullWhen;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;

@ToString
@RequiredArgsConstructor
@Getter
@NotNullWhen(property = "city", condition = NotNullWhenDemoRequest.Condition.class)
public class NotNullWhenDemoRequest {

    private final String city;

    private final String street;

    public static class Condition implements Predicate<NotNullWhenDemoRequest> {

        @Override
        public boolean test(NotNullWhenDemoRequest request) {
            return StringUtils.hasText(request.getStreet());
        }
    }
}
