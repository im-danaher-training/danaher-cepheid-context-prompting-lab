package com.cepheid.training.order;

// NOTE: Requirements below are intentionally left somewhat ambiguous. Participants
// should use Copilot to clarify assumptions before extending this class.
public class ValidationService {

    // Orders are considered valid if every line has a positive quantity that does
    // not exceed available stock, and the order has at least one line.
    public boolean isValid(Order order) {
        if (order.getLines().isEmpty()) {
            return false;
        }
        for (OrderLine line : order.getLines()) {
            if (line.getQuantity() <= 0) {
                return false;
            }
            if (line.getQuantity() > line.getProduct().getAvailableStock()) {
                return false;
            }
        }
        return true;
    }
}
