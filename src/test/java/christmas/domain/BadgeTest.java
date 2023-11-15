package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {

    @DisplayName("금액별 배지 적용 테스트")
    @Test
    void getBadgeByDiscount() {
        assertThat(Badge.getBadgeByDiscount(4999)).isEqualTo(Badge.NONE);

        assertThat(Badge.getBadgeByDiscount(5000)).isEqualTo(Badge.STAR);
        assertThat(Badge.getBadgeByDiscount(9999)).isEqualTo(Badge.STAR);

        assertThat(Badge.getBadgeByDiscount(10000)).isEqualTo(Badge.TREE);
        assertThat(Badge.getBadgeByDiscount(19999)).isEqualTo(Badge.TREE);

        assertThat(Badge.getBadgeByDiscount(20000)).isEqualTo(Badge.SANTA);
        assertThat(Badge.getBadgeByDiscount(120000)).isEqualTo(Badge.SANTA);
    }
}