package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasServiceTest {
    private final ChristmasService christmasService;
    private final Order noEventOrder;
    private final Order eventOrder;

    public ChristmasServiceTest() {
        this.christmasService = new ChristmasService();

        HashMap<Menu, Integer> noEventMenus = new HashMap<>();
        noEventMenus.put(Menu.TAPAS, 1);
        noEventMenus.put(Menu.COKE, 1);
        this.noEventOrder = new Order(26, noEventMenus);

        HashMap<Menu, Integer> eventMenus = new HashMap<>();
        eventMenus.put(Menu.STAKE, 1);
        eventMenus.put(Menu.BARBECUE, 1);
        eventMenus.put(Menu.CAKE, 2);
        eventMenus.put(Menu.COKE, 1);
        this.eventOrder = new Order(3, eventMenus);
    }

    @DisplayName("증정 메뉴 테스트")
    @Test
    void getPresentation() {
        assertThat(christmasService.getPresentation(noEventOrder))
                .isEqualTo("없음");

        assertThat(christmasService.getPresentation(eventOrder))
                .isEqualTo("샴페인 1개");
    }

    @DisplayName("혜택 내역 이벤트 테스트")
    @Test
    void getAppliedEvents() {
        assertThat(christmasService.getAppliedEvents(noEventOrder))
                .isEqualTo("없음");

        assertThat(christmasService.getAppliedEvents(eventOrder))
                .contains("크리스마스 디데이 할인: -1,200원", "평일 할인: -4,046원", "특별 할인: -1,000원", "증정 이벤트: -25,000원");
    }

    @DisplayName("총혜택 금액 테스트")

    @Test
    void getTotalDiscountPrice() {
        assertThat(christmasService.getTotalDiscountPrice(noEventOrder))
                .isEqualTo(0);

        eventOrder.updateTotalDiscountPrice(Event.PRESENTATION.getDiscountPrice());
        eventOrder.updateTotalDiscountPrice(Event.SPECIAL.getDiscountPrice());
        assertThat(christmasService.getTotalDiscountPrice(eventOrder))
                .isEqualTo(26000);
    }

    @DisplayName("12월 이벤트 배지 테스트")
    @Test
    void getBadge() {
        assertThat(christmasService.getBadge(noEventOrder))
                .isEqualTo(Badge.NONE.toString());

        eventOrder.updateTotalDiscountPrice(Event.PRESENTATION.getDiscountPrice());
        assertThat(christmasService.getBadge(eventOrder))
                .isEqualTo(Badge.SANTA.toString());
    }

    @DisplayName("할인 후 예상 결제 금액 테스트")
    @Test
    void getTotalPriceAfterDiscount() {
        assertThat(christmasService.getTotalPriceAfterDiscount(noEventOrder))
                .isEqualTo(noEventOrder.getTotalPriceBeforeDiscount());

        // 샴페인 주문 안했을 시 샴페인 값 다시 더하기
        eventOrder.updateTotalDiscountPrice(Event.PRESENTATION.getDiscountPrice());
        assertThat(christmasService.getTotalPriceAfterDiscount(eventOrder))
                .isEqualTo(eventOrder.getTotalPriceBeforeDiscount() - eventOrder.getTotalDiscountPrice() + Event.PRESENTATION.getDiscountPrice());
    }
}