package christmas.service;

import christmas.DecimalUtil;
import christmas.domain.Event;
import christmas.domain.Order;
import org.mockito.internal.matchers.Or;

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
            if (order.checkAppliedEvents(event) && order.getDiscountPrice(event) > 0) {
                String discountInfo = String.format("%s%s원",
                        event,
                        DecimalUtil.convertToFormat(order.getDiscountPrice(event))
                );

                result.add(discountInfo);
            }
        }

        return result;
    }

    public Integer getTotalDiscountPrice(Order order) {
        int totalDiscountPrice = 0;

        for (Event event : Event.values()) {
            if (order.checkAppliedEvents(event) && order.getDiscountPrice(event) > 0) {
                totalDiscountPrice += order.getDiscountPrice(event);
            }
        }

        return totalDiscountPrice;
    }
}
