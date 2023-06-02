package br.com.leandro.lsfood.order.domain.model;

public enum OrderStatus {
    NEW,
    CANCELED,
    PAID,
    NOT_AUTHORIZED,
    CONFIRMED,
    READY,
    OUT_FOR_DELIVERY,
    DELIVERED;
}
