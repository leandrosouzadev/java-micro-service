package br.com.leandro.lsfood.order.domain.service;

import br.com.leandro.lsfood.order.domain.dto.OrderDTO;

import br.com.leandro.lsfood.order.domain.dto.OrderStatusDTO;
import br.com.leandro.lsfood.order.domain.model.Order;
import br.com.leandro.lsfood.order.domain.model.OrderStatus;
import br.com.leandro.lsfood.order.domain.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .toList();
    }

    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);

        order.setDateTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        order.getItems().forEach(item -> item.setOrder(order));
        Order savedOrder = orderRepository.save(order);

        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    public OrderDTO updateStatus(Long id, OrderStatusDTO orderStatusDTO) {

        Order order = orderRepository.findByIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setOrderStatus(orderStatusDTO.getOrderStatus());
        orderRepository.updateStatus(orderStatusDTO.getOrderStatus(), order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public void approveOrderPayment(Long id) {

        Order order = orderRepository.findByIdWithItems(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.updateStatus(OrderStatus.PAID, order);
    }
}
