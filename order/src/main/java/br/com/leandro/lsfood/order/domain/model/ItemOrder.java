package br.com.leandro.lsfood.order.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer quantity;

    @Column(length = 255)
    private String description;

    @ManyToOne(optional=false)
    private Order order;
}
