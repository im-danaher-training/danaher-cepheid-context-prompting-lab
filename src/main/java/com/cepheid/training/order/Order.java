package com.cepheid.training.order;

import java.util.List;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final List<OrderLine> lines;
    private OrderStatus status;

    public Order(String orderId, Customer customer, List<OrderLine> lines) {
        this.orderId = orderId;
        this.customer = customer;
        this.lines = lines;
        this.status = OrderStatus.NEW;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotal() {
        return lines.stream()
                .mapToDouble(line -> line.getProduct().getUnitPrice() * line.getQuantity())
                .sum();
    }
}
