package br.com.leandro.lsfood.payments.repository;

import br.com.leandro.lsfood.payments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
