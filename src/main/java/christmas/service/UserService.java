package christmas.service;

import christmas.Constants;
import christmas.domain.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class UserService {
    public Integer getVisitDate(String visitDate) {
        validateVisitDate(visitDate);

        return Integer.parseInt(visitDate);
    }

    private void validateVisitDate(String visitDate) {
        try {
            int date = Integer.parseInt(visitDate);

            if (date < Constants.MIN_VISIT_DATE || date > Constants.MAX_VISIT_DATE) {
                throw new IllegalArgumentException(Constants.VISIT_DATE_ERROR);
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Constants.VISIT_DATE_ERROR);
        }
    }

    public HashMap<Menu, Integer> getMenus(String menusInput) {
        String[] orders = menusInput.split(",");

        HashMap<Menu, Integer> result = new HashMap<>();
        for (String order : orders) {
            Menu menu = getOrderMenu(order);
            Integer count = getOrderCount(order);

            validateDuplicateOrder(result, menu);

            result.put(menu, count);
        }

        validateOrderResult(result);
        return result;
    }

    private void validateDuplicateOrder(HashMap<Menu, Integer> result, Menu menu) {
        if (result.containsKey(menu)) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private void validateOrderResult(HashMap<Menu, Integer> result) {
        if (Menu.onlyDrink(List.copyOf(result.keySet()))) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }

        int totalCount = 0;
        for (Menu menu : result.keySet()) {
            totalCount += result.get(menu);
        }

        if (totalCount > Constants.MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private Menu getOrderMenu(String order) {
        String menu = order.split("-")[0];

        validateOrderMenu(menu);

        return Menu.getMenu(menu);
    }

    private void validateOrderMenu(String menu) {
        try {
            Menu.getMenu(menu);
        } catch (NoSuchElementException exception) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private Integer getOrderCount(String order) {
        String count = order.split("-")[1];

        validateOrderCount(count);

        return Integer.parseInt(count);
    }

    private void validateOrderCount(String count) {
        try {
            int c = Integer.parseInt(count);

            if (c < Constants.MIN_ORDER_COUNT) {
                throw new IllegalArgumentException(Constants.ORDER_ERROR);
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }
}
