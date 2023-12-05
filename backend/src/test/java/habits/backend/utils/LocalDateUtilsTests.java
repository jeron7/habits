package habits.backend.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Tag("unit")
public class LocalDateUtilsTests {

    @Nested
    @DisplayName("getDayOfDaysAgo tests")
    class TestGetDateOfDaysAgo {

        @DisplayName("Should work with days parameter with value greater than zero")
        @ParameterizedTest
        @ValueSource(ints = {1, 15, 30})
        void testValidInputs(int days) {
            LocalDate todayDate = LocalDate.now();

            LocalDate dateDaysAgo = LocalDateUtils.getDateOfDaysAgo(days);

            assertThat(dateDaysAgo)
                    .isEqualTo(todayDate.minusDays(days));
        }

        @DisplayName("Should fail due to days parameter with value less than one")
        @ParameterizedTest
        @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
        void testInvalidInputs(int days) {
            assertThatThrownBy(() -> LocalDateUtils.getDateOfDaysAgo(days))
                    .isInstanceOf(AssertionError.class)
                    .hasMessage("days must be greater than zero");
        }
    }
}
