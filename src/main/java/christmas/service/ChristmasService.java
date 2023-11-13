package christmas.service;

import christmas.DecimalUtil;
import christmas.domain.Event;
import christmas.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class ChristmasService {
    public String getPresentation(Order order) {
        if (order.checkPresentation()) return "샴페인 1개";

        return "없음";
    }

    public String getAppliedEvents(Order order) {
        List<String> result = new ArrayList<>();

        result.add(getDDayDiscount(order));
        result.add(getWeekDayDiscount(order));

        return String.join("\n", result);
    }

    private String getDDayDiscount(Order order) {
        int discountPrice = order.getDDayDiscount();

        if (discountPrice == 0) return "";
        return String.format("%s%s원", Event.D_DAY, DecimalUtil.convertToFormat(discountPrice));
    }

    private String getWeekDayDiscount(Order order) {
        int discountPrice = order.getWeekDayDiscount();

        if (discountPrice == 0) return "";
        return String.format("%s%s원", Event.WEEKDAY, DecimalUtil.convertToFormat(discountPrice));
    }
}
