package br.com.leandro.lsfood.payments.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrder {

    private Long id;
    private Integer quantity;
    private String description;

}
