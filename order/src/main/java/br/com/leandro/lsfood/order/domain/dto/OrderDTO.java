package br.com.leandro.lsfood.order.domain.dto;

import br.com.leandro.lsfood.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime dateTime;
    private OrderStatus status;
    private List<ItemOrderDTO> items = new ArrayList<>();
}
