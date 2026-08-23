package com.cepheid.training.order;

public class OrderService {
    private final ValidationService validationService;
    private final OrderRepository orderRepository;

    public OrderService(ValidationService validationService, OrderRepository orderRepository) {
        this.validationService = validationService;
        this.orderRepository = orderRepository;
    }

    // Submits an order if it passes validation, otherwise marks it rejected.
    // Ask Copilot to explain what happens to the persisted state in each branch.
    public Order submit(Order order) {
        if (validationService.isValid(order)) {
            order.setStatus(OrderStatus.SUBMITTED);
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        orderRepository.save(order);
        return order;
    }
}
