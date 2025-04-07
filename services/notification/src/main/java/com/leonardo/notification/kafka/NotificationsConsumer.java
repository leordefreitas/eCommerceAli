package com.leonardo.notification.kafka;

import com.leonardo.notification.email.EmailService;
import com.leonardo.notification.kafka.order.OrderConfirmation;
import com.leonardo.notification.kafka.payment.PaymentConfirmation;
import com.leonardo.notification.notification.Notification;
import com.leonardo.notification.notification.NotificationRepository;
import com.leonardo.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationsConsumer {

    private final NotificationRepository repository;
     private final EmailService emailService;

    // aqui ele esta conectando o que ele fez no microservice do payment pelo kafka
    // para verificar se o payment vai ser bem sucedido ou nao se forma assyncrona, pois o pagament
    // pode demorar para ser realizado
//    @KafkaListener(topics = "payment-topic")
    @KafkaListener(topics = "paymentTopic", groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation ));
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // send email
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    //    @KafkaListener(topics = "order-topic")
    @KafkaListener(topics = "orderTopic", groupId = "orderGroup")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation ));
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // send email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }



}
