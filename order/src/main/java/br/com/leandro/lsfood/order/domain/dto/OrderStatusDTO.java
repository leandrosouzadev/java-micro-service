package br.com.leandro.lsfood.order.domain.dto;

import br.com.leandro.lsfood.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {
    private OrderStatus orderStatus;
}
