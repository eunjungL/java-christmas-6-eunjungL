package christmas;

public class Constants {
    /** 이벤트 플래너에서 사용되는 문구 */
    public static final String START_COMMENT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String GET_VISIT_DATE_COMMENT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String GET_ORDER_MENU_COMMENT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String EVENT_COMMENT = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    /** 이벤트 플래너에서 사용되는 숫자 */
    public static final int MIN_VISIT_DATE = 1;
    public static final int MAX_VISIT_DATE = 31;
    public static final int MIN_ORDER_COUNT = 1;
    public static final int MAX_ORDER_COUNT = 20;
    public static final int PRESENTATION_PRICE = 120000;
    public static final int D_DAY_LAST_DAY = 25;

    /** 에러 메시지 */
    private static final String ERROR_PREFIX = "[ERROR]";
    public static final String VISIT_DATE_ERROR = ERROR_PREFIX + "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ORDER_ERROR = ERROR_PREFIX + "유효하지 않은 주문입니다. 다시 입력해 주세요.";
}
