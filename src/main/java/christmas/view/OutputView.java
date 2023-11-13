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

    public void printEventComment(Integer visitDate) {
        System.out.printf(Constants.EVENT_COMMENT + "\n", visitDate);
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

    public void printTotalDiscountPrice(Integer result) {
        System.out.println("<총혜택 금액>");

        String format = "-%s원\n%n";
        if (result == 0) {
            format = "%s원\n%n";
        }

        System.out.printf(format, DecimalUtil.convertToFormat(result));
    }

    public void printTotalPriceAfterDiscount(Integer result) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(DecimalUtil.convertToFormat(result) + "원\n");
    }

    public void printBadge(String result) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(result);
    }
}
