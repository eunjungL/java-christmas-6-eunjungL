package christmas.service;

import christmas.DecimalUtil;
import christmas.domain.Event;
import christmas.domain.Order;

import java.text.DecimalFormat;

public class ChristmasService {
    public String getPresentation(Order order) {
        if (order.checkPresentation()) return "샴페인 1개";

        return "없음";
    }

    public String getAppliedEvents(Order order) {
        StringBuilder result = new StringBuilder();

        result.append(getDDayDiscount(order));

        return result.toString();
    }

    private String getDDayDiscount(Order order) {
        int discountPrice = order.getDDayDiscount();

        if (discountPrice == 0) return "";
        return String.format("%s%s원", Event.D_DAY, DecimalUtil.convertToFormat(discountPrice));
    }
}
