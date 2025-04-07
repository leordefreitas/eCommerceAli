package com.leonardo.order.order;

import com.leonardo.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Must be a customer id")
        @NotEmpty(message = "Must be a customer id")
        @NotBlank(message = "Must be a customer id")
        String customerId,
        // usou o not empty pois ele serve para array
        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> products
) {
}
