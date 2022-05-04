package net.croz.nrichdemobackend.validation.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.croz.nrich.validation.api.constraint.NullWhen;

import java.math.BigDecimal;
import java.util.function.Predicate;

@ToString
@RequiredArgsConstructor
@Getter
@NullWhen(property = "amountForRefund", condition = NullWhenDemoRequest.Condition.class)
public class NullWhenDemoRequest {

    private final BigDecimal amountForRefund;

    private final PaymentType paymentType;

    public static class Condition implements Predicate<NullWhenDemoRequest> {
        @Override
        public boolean test(NullWhenDemoRequest request) {
            return PaymentType.COMPANY_CREDIT_CARD == request.getPaymentType();
        }
    }

    public enum PaymentType {
        COMPANY_CREDIT_CARD, OTHER
    }
}
