package br.com.leandro.lsfood.payments.api.controller;

import br.com.leandro.lsfood.payments.domain.dto.PaymentDTO;
import br.com.leandro.lsfood.payments.domain.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Page<PaymentDTO> findAll(@PageableDefault Pageable pageable) {
        return paymentService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable @NotNull Long id) {
        PaymentDTO paymentDTO = paymentService.findById(id);
        return ResponseEntity.ok(paymentDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> save(@RequestBody @Valid PaymentDTO paymentDTO, UriComponentsBuilder uriComponentsBuilder) {
        PaymentDTO savedPayment = paymentService.save(paymentDTO);
        URI uri = uriComponentsBuilder.path("/payments/{id}").buildAndExpand(paymentDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(savedPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentDTO updatedPayment = paymentService.update(id, paymentDTO);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> delete(@PathVariable @NotNull Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/approve-payment")
    @CircuitBreaker(name = "updateOrder", fallbackMethod = "paymentAuthorizedPendingIntegration")
    public void approvePayment(@PathVariable @NotNull Long id) {
        paymentService.approvePayment(id);
    }

    public void paymentAuthorizedPendingIntegration(Long id, Exception e) {
        paymentService.changeStatusPendingIntegration(id);
    }
}
