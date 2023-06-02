package br.com.leandro.lsfood.payments.domain.repository;

import br.com.leandro.lsfood.payments.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
