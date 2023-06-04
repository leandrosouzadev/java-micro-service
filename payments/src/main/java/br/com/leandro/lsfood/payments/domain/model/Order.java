package br.com.leandro.lsfood.payments.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private List<ItemOrder> items;
}
