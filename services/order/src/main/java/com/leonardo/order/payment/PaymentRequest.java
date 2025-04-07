package com.leonardo.order.payment;

import com.leonardo.order.customer.CustomerResponse;
import com.leonardo.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
