package christmas.service;

import christmas.DecimalUtil;
import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class ChristmasService {
    public String getPresentation(Order order) {
        if (order.checkAppliedEvents(Event.PRESENTATION)) return "샴페인 1개";

        return "없음";
    }

    public String getAppliedEvents(Order order) {
        List<String> result = getDiscountInfo(order);

        if (result.size() == 0) return "없음";
        return String.join("\n", result);
    }

    private List<String> getDiscountInfo(Order order) {
        List<String> result = new ArrayList<>();

        for (Event event : Event.values()) {
            int discountPrice = order.getDiscountPrice(event);

            if (order.checkAppliedEvents(event) && discountPrice > 0) {
                String discountInfo = String.format("%s%s원",
                        event,
                        DecimalUtil.convertToFormat(order.getDiscountPrice(event))
                );

                result.add(discountInfo);
                order.updateTotalDiscountPrice(discountPrice);
            }
        }

        return result;
    }

    public Integer getTotalDiscountPrice(Order order) {
        return order.getTotalDiscountPrice();
    }

    public String getBadge(Order order) {
        Badge badge = Badge.getBadgeByDiscount(order.getTotalDiscountPrice());

        return badge.toString();
    }

    public Integer getTotalPriceAfterDiscount(Order order) {
        return order.getTotalPriceAfterDiscount();
    }
}
