package christmas.view;

import christmas.Constants;

public class OutputView {
    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printStartComment() {
        System.out.println(Constants.START_COMMENT);
    }
}
