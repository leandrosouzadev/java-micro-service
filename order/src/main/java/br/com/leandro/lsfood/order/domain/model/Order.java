package br.com.leandro.lsfood.order.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 100)
    private OrderStatus orderStatus;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="order")
    private List<ItemOrder> items = new ArrayList<>();
}
