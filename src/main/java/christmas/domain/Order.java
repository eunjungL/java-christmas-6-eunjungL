package christmas.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private Integer visitDate;
    private HashMap<Menu, Integer> order;

    public Order(Integer visitDate, HashMap<Menu, Integer> order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public Integer getTotalPriceBeforeDiscount() {
        int totalPrice = 0;

        for (Menu menu : order.keySet()) {
            totalPrice += menu.getPrice() * order.get(menu);
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();

        for (Menu menu : order.keySet()) {
            result.add(String.format("%s %d개", menu.getName(), order.get(menu)));
        }

        return String.join("\n", result);
    }
}
