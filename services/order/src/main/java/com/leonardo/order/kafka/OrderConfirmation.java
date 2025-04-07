package com.leonardo.order.kafka;

import com.leonardo.order.customer.CustomerResponse;
import com.leonardo.order.order.PaymentMethod;
import com.leonardo.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
