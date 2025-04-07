package com.leonardo.order.order;

import com.leonardo.order.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
