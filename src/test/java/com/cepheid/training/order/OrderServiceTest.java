package com.cepheid.training.order;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    @Test
    void submitsValidOrderSuccessfully() {
        Customer customer = new Customer("CUST-1", "Fictional Regional Lab", "US-WEST");
        Product product = new Product("CEPH-100", "GeneXpert Cartridge A", 25.0, 10);
        Order order = new Order("ORD-1", customer, List.of(new OrderLine(product, 2)));

        OrderService service = new OrderService(new ValidationService(), new OrderRepository());
        Order result = service.submit(order);

        assertEquals(OrderStatus.SUBMITTED, result.getStatus());
    }

    @Test
    void rejectsOrderExceedingAvailableStock() {
        Customer customer = new Customer("CUST-2", "Fictional Diagnostic Center", "US-EAST");
        Product product = new Product("CEPH-200", "GeneXpert Cartridge B", 30.0, 1);
        Order order = new Order("ORD-2", customer, List.of(new OrderLine(product, 5)));

        OrderService service = new OrderService(new ValidationService(), new OrderRepository());
        Order result = service.submit(order);

        assertEquals(OrderStatus.REJECTED, result.getStatus());
    }
}
