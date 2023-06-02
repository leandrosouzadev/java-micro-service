package br.com.leandro.lsfood.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDTO {
    private Long id;
    private Integer quantity;
    private String description;
}
