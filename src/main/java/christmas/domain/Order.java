package christmas.domain;

import christmas.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private final Integer visitDate;
    private final HashMap<Menu, Integer> order;

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

    public Boolean checkPresentation() {
        return getTotalPriceBeforeDiscount() >= Constants.PRESENTATION_PRICE;
    }

    public Integer getDDayDiscount() {
        if (visitDate > Constants.D_DAY_LAST_DAY) return 0;

        return 1000 + (visitDate - 1) * Event.D_DAY.getDiscountPrice();
    }

    public Integer getWeekDayDiscount() {
        int discountPrice = 0;

        if (Event.checkWeekDay(visitDate)) {
            discountPrice += Event.WEEKDAY.getDiscountPrice() * Menu.getMenuCountByMenuType(order, MenuType.DESSERT);
        }

        return discountPrice;
    }

    public Integer getWeekEndDiscount() {
        int discountPrice = 0;

        if (Event.checkWeekEnd(visitDate)) {
            discountPrice += Event.WEEKEND.getDiscountPrice() * Menu.getMenuCountByMenuType(order, MenuType.MAIN);
        }

        return discountPrice;
    }

    public Integer getPresentationDiscount() {
        int discountPrice = 0;

        if (checkPresentation()) {
            discountPrice += Event.PRESENTATION.getDiscountPrice();
        }

        return discountPrice;
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
