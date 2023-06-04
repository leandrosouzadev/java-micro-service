package br.com.leandro.lsfood.payments.domain.http;

import br.com.leandro.lsfood.payments.domain.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-ms")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}/paid")
    void approveOrderPayment(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}")
    Order getOrderItems(@PathVariable Long id);
}
