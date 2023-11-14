package christmas.service;

import christmas.domain.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {
    private final UserService userService;

    public UserServiceTest() {
        this.userService = new UserService();
    }

    @DisplayName("방문 날짜 입력 예외 처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "a"})
    void validateGetVisitDateTest(String visitDate) {
        assertThatThrownBy(() -> {
            userService.getVisitDate(visitDate);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜 Integer로 변환 테스트")
    @Test
    void getVisitDateTest() {
        String visitDate = "20";

        assertThat(userService.getVisitDate(visitDate))
                .isEqualTo(20);
    }

    @DisplayName("주문 입력 예외 처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-0", "타파스-19,레드와인-2", "파스타-2", "레드와인-1,제로콜라-1", "티본스테이크-1,티본스테이크-2"})
    void validateGetMenusTest(String menus) {
        assertThatThrownBy(() -> {
            userService.getMenus(menus);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴 HashMap 변환 테스트")
    @Test

    void getMenusTest() {
        String menus = "해산물파스타-1,바비큐립-2";

        HashMap<Menu, Integer> result = new HashMap<>();
        result.put(Menu.SEAFOOD_PASTA, 1);
        result.put(Menu.BARBECUE, 2);

        assertThat(userService.getMenus(menus))
                .isEqualTo(result);
    }
}