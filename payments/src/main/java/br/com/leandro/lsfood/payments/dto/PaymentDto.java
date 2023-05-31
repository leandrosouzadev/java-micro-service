package br.com.leandro.lsfood.payments.dto;

import br.com.leandro.lsfood.payments.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {

    private Long id;
    private BigDecimal amount;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private PaymentStatus paymentStatus;
    private Long orderId;
    private Long paymentMethodId;

}
