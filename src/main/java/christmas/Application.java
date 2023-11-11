package christmas;

import christmas.controller.ChristmasController;
import christmas.service.UserService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        UserService userService = new UserService();
        ChristmasController christmasController = new ChristmasController(inputView, outputView, userService);

        christmasController.run();
    }
}
