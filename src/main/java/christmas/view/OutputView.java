package christmas.view;

import christmas.Constants;
import christmas.DecimalUtil;

public class OutputView {
    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printStartComment() {
        System.out.println(Constants.START_COMMENT);
    }

    public void printEventComment() {
        System.out.println(Constants.EVENT_COMMENT);
    }

    public void printOrderResult(String order) {
        System.out.println("<주문 메뉴>");
        System.out.println(order + "\n");
    }

    public void printTotalPriceBeforeDiscount(Integer totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(DecimalUtil.convertToFormat(totalPrice) + "원" + "\n");
    }

    public void printPresentation(String result) {
        System.out.println("<증정 메뉴>");
        System.out.println(result + "\n");
    }

    public void printAppliedEvents(String result) {
        System.out.println("<혜택 내역>");
        System.out.println(result + "\n");
    }
}
