package com.leonardo.notification.kafka.order;

import org.springframework.validation.annotation.Validated;

// neste caso nao precisa de validation pois ele nao trabalhara necessariamente
// como banco de dados
public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
