package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuTest {

    @DisplayName("없는 메뉴 일시 예외 처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"바베큐", "파스타", "바베큐립", "스테이크"})
    void validateGetMenu(String name) {
        assertThatThrownBy(() -> {
            Menu.getMenu(name);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("메뉴 이름으로 메뉴 찾기 테스트")
    @Test
    void getMenu() {
        String name = "바비큐립";

        assertThat(Menu.getMenu(name)).isEqualTo(Menu.BARBECUE);
    }

    @DisplayName("메뉴에 음료만 있는지 확인하는 테스트")
    @Test
    void isOnlyDrink() {
        List<Menu> menus = new ArrayList<>();

        menus.add(Menu.WINE);
        menus.add(Menu.COKE);
        assertThat(Menu.isOnlyDrink(menus)).isEqualTo(Boolean.TRUE);

        menus.add(Menu.TAPAS);
        assertThat(Menu.isOnlyDrink(menus)).isEqualTo(Boolean.FALSE);
    }

    @DisplayName("메뉴 타입 카운트 테스트")
    @Test
    void getMenuCountByMenuType() {
        HashMap<Menu, Integer> menus = new HashMap<>();
        menus.put(Menu.STAKE, 2);
        menus.put(Menu.CHRISTMAS_PASTA, 1);
        menus.put(Menu.CAKE, 3);
        menus.put(Menu.ICE_CREAM, 2);

        assertThat(Menu.getMenuCountByMenuType(menus, MenuType.DESSERT)).isEqualTo(5);
        assertThat(Menu.getMenuCountByMenuType(menus, MenuType.MAIN)).isEqualTo(3);
    }
}