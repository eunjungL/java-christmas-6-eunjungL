package christmas.domain;

import christmas.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private final Integer visitDate;
    private final HashMap<Menu, Integer> order;
    private final List<Event> appliedEvents;
    private Integer totalDiscountPrice;

    public Order(Integer visitDate, HashMap<Menu, Integer> order) {
        this.visitDate = visitDate;
        this.order = order;
        this.appliedEvents = getAppliedEvents();
        this.totalDiscountPrice = 0;
    }

    public Integer getTotalPriceBeforeDiscount() {
        int totalPrice = 0;

        for (Menu menu : order.keySet()) {
            totalPrice += menu.getPrice() * order.get(menu);
        }

        return totalPrice;
    }

    private List<Event> getAppliedEvents() {
        List<Event> appliedEvents = new ArrayList<>();

        for (Event event : Event.values()) {
            if (event.checkApplied(getTotalPriceBeforeDiscount(), visitDate)) {
                appliedEvents.add(event);
            }
        }

        return appliedEvents;
    }

    public Boolean checkAppliedEvents(Event event) {
        return appliedEvents.contains(event);
    }

    public Integer getDiscountPrice(Event event) {
        if (event == Event.D_DAY)
            return 1000 + (visitDate - 1) * event.getDiscountPrice();
        if (event == Event.WEEKDAY)
            return event.getDiscountPrice() * Menu.getMenuCountByMenuType(order, MenuType.DESSERT);
        if (event == Event.WEEKEND)
            return event.getDiscountPrice() * Menu.getMenuCountByMenuType(order, MenuType.MAIN);

        return event.getDiscountPrice();
    }

    public void updateTotalDiscountPrice(Integer discountPrice) {
        totalDiscountPrice += discountPrice;
    }

    public Integer getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public Integer getTotalPriceAfterDiscount() {
        int totalPrice = getTotalPriceBeforeDiscount() - totalDiscountPrice;

        if (!order.containsKey(Menu.CHAMPAGNE)) {
            totalPrice += Event.PRESENTATION.getDiscountPrice();
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
