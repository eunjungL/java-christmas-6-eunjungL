package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @DisplayName("이벤트 미적용 테스트")
    @Test
    void checkNoApplied() {
        int minimumPrice = 9999;
        int visitDate = 1;

        assertThat(Event.D_DAY.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.FALSE);

        minimumPrice = 119000;
        assertThat(Event.PRESENTATION.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.FALSE);
        assertThat(Event.WEEKDAY.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.FALSE);

        visitDate = 3;
        assertThat(Event.WEEKEND.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.FALSE);
    }

    @DisplayName("이벤트 적용 테스트")
    @Test
    void checkApplied() {
        int minimumPrice = 10000;
        int visitDate = 1;

        assertThat(Event.D_DAY.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.TRUE);

        minimumPrice = 120000;
        assertThat(Event.PRESENTATION.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.TRUE);
        assertThat(Event.WEEKEND.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.TRUE);

        visitDate = 3;
        assertThat(Event.WEEKDAY.checkApplied(minimumPrice, visitDate)).isEqualTo(Boolean.TRUE);
    }
}