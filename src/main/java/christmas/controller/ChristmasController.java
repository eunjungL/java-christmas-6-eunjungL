package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.ChristmasService;
import christmas.service.UserService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final UserService userService;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView, UserService userService, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userService = userService;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.printStartComment();

        Order order = getOrder();

        getPresentation(order);
        getAppliedEvents(order);
        getTotalDiscountPrice(order);

        getBadge(order);
    }

    private Order getOrder() {
        Integer visitDate = getVisitDate();
        HashMap<Menu, Integer> menus = getMenus();

        Order order = new Order(visitDate, menus);

        outputView.printEventComment(visitDate);
        outputView.printOrderResult(order.toString());
        outputView.printTotalPriceBeforeDiscount(order.getTotalPriceBeforeDiscount());

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

    private void getPresentation(Order order) {
        String result = christmasService.getPresentation(order);
        outputView.printPresentation(result);
    }

    private void getAppliedEvents(Order order) {
        String result = christmasService.getAppliedEvents(order);
        outputView.printAppliedEvents(result);
    }

    private void getTotalDiscountPrice(Order order) {
        Integer result = christmasService.getTotalDiscountPrice(order);
        outputView.printTotalDiscountPrice(result);
    }

    private void getBadge(Order order) {
        String result = christmasService.getBadge(order);
        outputView.printBadge(result);
    }
}
