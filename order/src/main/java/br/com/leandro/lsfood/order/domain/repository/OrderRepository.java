package br.com.leandro.lsfood.order.domain.repository;

import br.com.leandro.lsfood.order.domain.model.Order;
import br.com.leandro.lsfood.order.domain.model.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.orderStatus = :status where o = :order")
    void updateStatus(OrderStatus status, Order order);

    @Query(value = "SELECT o from Order o LEFT JOIN FETCH o.items where o.id = :id")
    Order findByIdWithItems(Long id);

}
