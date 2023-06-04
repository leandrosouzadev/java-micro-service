package br.com.leandro.lsfood.payments.domain.model;

public enum PaymentStatus {
    CREATED,
    CONFIRMED,
    CONFIRMED_NO_INTEGRATION,
    CANCELED;
}
