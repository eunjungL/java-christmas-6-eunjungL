package christmas.controller;

import christmas.domain.Menu;
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

        Integer visitDate = getVisitDate();
        HashMap<Menu, Integer> order = getOrder();
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

    private HashMap<Menu, Integer> getOrder() {
        while (true) {
            try {
                return userService.getOrder(inputView.getOrder());
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
