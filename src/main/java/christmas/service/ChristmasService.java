package christmas.service;

import christmas.domain.Order;

public class ChristmasService {
    public String getPresentation(Order order) {
        if (order.checkPresentation()) return "샴페인 1개";

        return "없음";
    }
}
