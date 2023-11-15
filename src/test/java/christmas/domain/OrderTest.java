package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    private final HashMap<Menu, Integer> noEventMenus;
    private final HashMap<Menu, Integer> eventMenus;

    public OrderTest() {
        this.noEventMenus = new HashMap<>();
        noEventMenus.put(Menu.TAPAS, 1);
        noEventMenus.put(Menu.COKE, 1);

        this.eventMenus = new HashMap<>();
        eventMenus.put(Menu.STAKE, 1);
        eventMenus.put(Menu.BARBECUE, 1);
        eventMenus.put(Menu.CAKE, 2);
        eventMenus.put(Menu.COKE, 1);
    }

    @DisplayName("할인 전 총주문 금액 테스트")
    @Test
    void getTotalPriceBeforeDiscount() {
        Order order = new Order(26, noEventMenus);

        assertThat(order.getTotalPriceBeforeDiscount())
                .isEqualTo(Menu.TAPAS.getPrice() + Menu.COKE.getPrice());
    }

    @DisplayName("총 주문 금액 10000원 이하 이벤트 미적용 테스트")
    @Test
    void checkAppliedNoEvents() {
        Order order = new Order(5, noEventMenus);

        assertThat(order.checkAppliedEvents(Event.D_DAY))
                .isEqualTo(Boolean.FALSE);
        assertThat(order.checkAppliedEvents(Event.PRESENTATION))
                .isEqualTo(Boolean.FALSE);
    }

    @DisplayName("이벤트 적용 테스트")
    @Test
    void checkAppliedEvents() {
        Order order = new Order(5, eventMenus);

        assertThat(order.checkAppliedEvents(Event.D_DAY))
                .isEqualTo(Boolean.TRUE);
        assertThat(order.checkAppliedEvents(Event.PRESENTATION))
                .isEqualTo(Boolean.TRUE);
    }


    @DisplayName("혜택 금액 테스트")
    @Test
    void getDiscountPrice() {
        Order order = new Order(5, eventMenus);

        assertThat(order.getDiscountPrice(Event.PRESENTATION))
                .isEqualTo(Event.PRESENTATION.getDiscountPrice());
        assertThat(order.getDiscountPrice(Event.D_DAY))
                .isEqualTo(1400);
    }

    @DisplayName("총 혜택 금액 업데이트 테스트")
    @Test
    void updateTotalDiscountPrice() {
        Order order = new Order(5, eventMenus);
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.SPECIAL));

        assertThat(order.getTotalDiscountPrice())
                .isEqualTo(1000);
    }

    @DisplayName("총 혜택 금액 테스트")
    @Test
    void getTotalDiscountPrice() {
        Order order = new Order(3, noEventMenus);
        assertThat(order.getTotalDiscountPrice()).isEqualTo(0);

        order = new Order(5, eventMenus);
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.PRESENTATION));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.SPECIAL));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.WEEKDAY));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.D_DAY));
        assertThat(order.getTotalDiscountPrice())
                .isEqualTo(31446);
    }

    @DisplayName("할인 후 예상 결제 금액 테스트")
    @Test
    void getTotalPriceAfterDiscount() {
        Order order = new Order(3, eventMenus);
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.PRESENTATION));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.SPECIAL));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.WEEKDAY));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.D_DAY));

        assertThat(order.getTotalPriceAfterDiscount())
                .isEqualTo(135754);

        // 샴페인 주문했을 시 결제 금액에서 이벤트 금액 차감
        eventMenus.put(Menu.CHAMPAGNE, 2);
        order = new Order(3, eventMenus);
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.PRESENTATION));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.SPECIAL));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.WEEKDAY));
        order.updateTotalDiscountPrice(order.getDiscountPrice(Event.D_DAY));

        assertThat(order.getTotalPriceAfterDiscount())
                .isEqualTo(160754);
    }
}