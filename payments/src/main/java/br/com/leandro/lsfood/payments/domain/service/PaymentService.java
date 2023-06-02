package br.com.leandro.lsfood.payments.domain.service;

import br.com.leandro.lsfood.payments.domain.dto.PaymentDTO;
import br.com.leandro.lsfood.payments.domain.model.Payment;
import br.com.leandro.lsfood.payments.domain.model.PaymentStatus;
import br.com.leandro.lsfood.payments.domain.repository.PaymentRepository;
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

    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setPaymentStatus(PaymentStatus.CREATED);
        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO update(Long id, PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setId(id);
        payment = paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    public Page<PaymentDTO> findAll(Pageable pageable) {
        return paymentRepository
                .findAll(pageable)
                .map(payment -> modelMapper.map(payment, PaymentDTO.class));
    }

    public PaymentDTO findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(payment, PaymentDTO.class);
    }
}
