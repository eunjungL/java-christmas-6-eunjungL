package christmas.service;

import christmas.DecimalUtil;
import christmas.domain.Event;
import christmas.domain.Order;
import org.mockito.internal.matchers.Or;

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
        result.add(getWeekEndDiscount(order));
        result.add(getPresentationDiscount(order));

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

    private String getWeekEndDiscount(Order order) {
        int discountPrice = order.getWeekEndDiscount();

        if (discountPrice == 0) return "";
        return String.format("%s%s원", Event.WEEKEND, DecimalUtil.convertToFormat(discountPrice));
    }

    private String getPresentationDiscount(Order order) {
        int discountPrice = order.getPresentationDiscount();

        if (discountPrice == 0) return  "";
        return String.format("%s%s원", Event.PRESENTATION, DecimalUtil.convertToFormat(discountPrice));
    }
}
