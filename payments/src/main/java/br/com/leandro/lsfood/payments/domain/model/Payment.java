package br.com.leandro.lsfood.payments.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column(precision = 15, scale = 2)
    private BigDecimal amount;

    @Size(max = 100)
    @Column(length = 100)
    private String name;

    @Size(max = 19)
    @Column(length = 19)
    private String number;

    @Size(max = 7)
    @Column(length = 7)
    private String expiration;

    @Size(min=3, max = 3)
    @Column(length = 3)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", length = 50)
    private PaymentStatus paymentStatus;

    @NotNull
    @Column(name = "order_id")
    private Long orderId;

    @NotNull
    @Column(name = "payment_method_id")
    private Long paymentMethodId;
}
