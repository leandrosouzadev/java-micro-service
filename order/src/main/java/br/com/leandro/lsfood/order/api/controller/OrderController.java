package br.com.leandro.lsfood.order.api.controller;

import br.com.leandro.lsfood.order.domain.dto.OrderDTO;
import br.com.leandro.lsfood.order.domain.dto.OrderStatusDTO;
import br.com.leandro.lsfood.order.domain.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable @NotNull Long id) {
        OrderDTO dto = orderService.findById(id);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> save(@RequestBody @Valid OrderDTO orderDTO, UriComponentsBuilder uriBuilder) {
        OrderDTO savedOrder = orderService.save(orderDTO);
        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.created(uri).body(savedOrder);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatusDTO status){
        OrderDTO dto = orderService.updateStatus(id, status);

        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> approveOrderPayment(@PathVariable @NotNull Long id) {
        orderService.approveOrderPayment(id);

        return ResponseEntity.ok().build();

    }
    
}
