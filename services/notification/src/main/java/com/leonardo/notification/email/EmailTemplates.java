package com.leonardo.notification.email;

import lombok.Getter;

// dessa forma que se faz para alterar um enum, pois ee quer setar os tipos
// de templates dos emails que serao enviados aos, usuarios
public enum EmailTemplates {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation")
    ;

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
