package com.leonardo.order.order;

import com.leonardo.order.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    // quando usado no modelo enum ele tem que colocar o enumtype
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String customerId;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    // data criada, colocada as opcoes para que ele na atualize nunca a data
    // e nao pode ser null
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    // caracteristicas implementadas para que ele tenha o comportamento
    // de atualizar sempre que for alterado alguma coisa dentro da classe
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

}
