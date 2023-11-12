package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.UserService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final UserService userService;

    public ChristmasController(InputView inputView, OutputView outputView, UserService userService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
    }

    public void run() {
        outputView.printStartComment();

        Order order = getOrder();

        outputView.printTotalPriceBeforeDiscount(order.getTotalPriceBeforeDiscount());
    }

    private Order getOrder() {
        Integer visitDate = getVisitDate();
        HashMap<Menu, Integer> menus = getMenus();

        Order order = new Order(visitDate, menus);
        outputView.printOrderResult(order.toString());

        return order;
    }

    private Integer getVisitDate() {
        while (true) {
            try {
                return userService.getVisitDate(inputView.getVisitDate());
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private HashMap<Menu, Integer> getMenus() {
        while (true) {
            try {
                return userService.getMenus(inputView.getMenus());
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
