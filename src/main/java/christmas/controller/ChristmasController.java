package christmas.controller;

import christmas.service.UserService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
}
