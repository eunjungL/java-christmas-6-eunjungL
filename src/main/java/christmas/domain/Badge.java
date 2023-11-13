package christmas.domain;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    public final Integer minimumPrice;

    Badge(String name, Integer minimumPrice) {
        this.name = name;
        this.minimumPrice = minimumPrice;
    }

    public static Badge getBadgeByDiscount(Integer totalDiscountPrice) {
        if (totalDiscountPrice >= SANTA.minimumPrice)
            return SANTA;
        if (totalDiscountPrice >= TREE.minimumPrice)
            return TREE;
        if (totalDiscountPrice >= STAR.minimumPrice)
            return  STAR;

        return NONE;
    }

    @Override
    public String toString() {
        return name;
    }
}
