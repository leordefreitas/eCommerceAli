package com.leonardo.notification.notification;

import com.leonardo.notification.kafka.order.OrderConfirmation;
import com.leonardo.notification.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation; // como algumas coisas neste microservice nao vao para banco de dados, podemos colocar a classe aqui sem o validation

}
