package com.leonardo.order.order;

import com.leonardo.order.customer.CustomerClient;
import com.leonardo.order.exception.BusinessException;
import com.leonardo.order.kafka.OrderConfirmation;
import com.leonardo.order.kafka.OrderProducer;
import com.leonardo.order.orderline.OrderLineRequest;
import com.leonardo.order.orderline.OrderLineResponse;
import com.leonardo.order.orderline.OrderLineService;
import com.leonardo.order.payment.PaymentClient;
import com.leonardo.order.payment.PaymentRequest;
import com.leonardo.order.product.ProductClient;
import com.leonardo.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // nao precisa indicar o constructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer producer;
    private final PaymentClient paymentClient;


    public Integer createOrder(@Valid OrderRequest request) {

        // check the customer -> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order:: No Customer exists with the provided ID"));

        // purchase the products -> product microservice (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        // persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
            request.amount(),
            request.paymentMethod(),
            order.getId(),
            order.getReference(),
            customer
        );
        paymentClient.requestOrderPayment(paymentRequest);


        // send the order confirmation -> notification microservice
        producer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with the provided Id: %d", orderId)
                ));
    }


}
