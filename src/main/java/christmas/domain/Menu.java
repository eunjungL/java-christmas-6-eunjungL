package christmas.domain;

import christmas.Constants;

import java.util.Arrays;
import java.util.List;

public enum Menu {
    SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    SALAD("시저샐러드", 8000, MenuType.APPETIZER),
    STAKE("티본스테이크", 55000, MenuType.MAIN),
    BARBECUE("바베큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),
    CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),
    COKE("제로콜라", 3000, MenuType.DRINK),
    WINE("레드와인", 60000, MenuType.DRINK),
    CHAMPAGNE("샴페인",25000, MenuType.DRINK)
    ;

    private final String name;
    private final Integer price;
    private final MenuType menuType;

    Menu(String name, Integer price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Menu getMenu(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElseThrow();
    }

    public static Boolean onlyDrink(List<Menu> menus) {
        for (Menu menu : menus) {
            if (menu.menuType != MenuType.DRINK) {
                return false;
            }
        }

        return true;
    }
}
