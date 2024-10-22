package seedu.address.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderTracker {
    private ArrayList<OrderHistory> history;
    private HashMap<Order, Integer> frequency;

    public OrderTracker() {
        this.history = new ArrayList<>();
        this.frequency = new HashMap<>();
    }

    public void add(Order order) {
        this.history.add(new OrderHistory(order));
        this.frequency.merge(order, 1, Integer::sum);
    }

    public int getTotalOrder() {
        int sum = 0;
        for (Map.Entry<Order, Integer> entry: this.frequency.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    /**
     * Clear all order tracked
     */
    public void clear() {
        this.history = new ArrayList<>();
        this.frequency = new HashMap<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(history, frequency);
    }

    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) {
            return true;
        }

        if (!(rhs instanceof OrderTracker)) {
            return false;
        }

        OrderTracker otherTracker = (OrderTracker) rhs;
        return history.equals(otherTracker.history)
                && frequency.equals(otherTracker.frequency);
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (OrderHistory orderHistory : this.history) {
            msg.append(orderHistory.toString()).append('\n');
        }
        return msg.toString();

    }
}
