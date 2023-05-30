package br.com.leandro.lsfood.payments.service;

import br.com.leandro.lsfood.payments.dto.PaymentDto;
import br.com.leandro.lsfood.payments.model.Payment;
import br.com.leandro.lsfood.payments.model.PaymentStatus;
import br.com.leandro.lsfood.payments.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PaymentDto save(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setPaymentStatus(PaymentStatus.CREATED);
        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto update(Long id, PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setId(id);
        payment = paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    public Page<PaymentDto> findAll(Pageable pageable) {
        return paymentRepository
                .findAll(pageable)
                .map(payment -> modelMapper.map(payment, PaymentDto.class));
    }

    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(payment, PaymentDto.class);
    }
}
