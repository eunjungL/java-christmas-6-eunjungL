package christmas.domain;

import christmas.Constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Event {
    D_DAY("크리스마스 디데이 할인", IntStream.range(Constants.MIN_VISIT_DATE, Constants.D_DAY_LAST_DAY).boxed().collect(Collectors.toList()), 100, 10000),
    WEEKDAY("평일 할인", List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31), 2023, 10000),
    WEEKEND("주말 할인", List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30), 2023, 10000),
    SPECIAL("특별 할인", List.of(3, 10, 17, 24, 25, 31), 1000, 10000),
    PRESENTATION("증정 이벤트", IntStream.range(Constants.MIN_VISIT_DATE, Constants.MAX_VISIT_DATE).boxed().collect(Collectors.toList()), 25000, 120000);

    private final String name;
    private final List<Integer> appliedDate;
    private final Integer discountPrice;
    private final Integer minimumPrice;

    Event(String name, List<Integer> appliedDate, Integer discountPrice, Integer minimumPrice) {
        this.name = name;
        this.appliedDate = appliedDate;
        this.discountPrice = discountPrice;
        this.minimumPrice = minimumPrice;
    }

    public Boolean checkApplied(Integer minimumPrice, Integer visitDate) {
        return minimumPrice >= this.minimumPrice && this.appliedDate.contains(visitDate);
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return String.format("%s: -", name);
    }
}
