package com.leonardo.payment.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer // quand voce usando o validation e coloca uma classe aqui voce precisa indicar aquele objeto como validated
) {
}
