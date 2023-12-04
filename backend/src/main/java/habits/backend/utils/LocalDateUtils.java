package habits.backend.utils;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class LocalDateUtils {

    public static LocalDate getDateOfDaysAgo(int days) {
        assert days > 0  : "days must be greater than zero";

        LocalDate today = LocalDate.now();

        return today.minusDays(days);
    }

    public static int getDayOfWeek(LocalDate date) {
        return date.get(WeekFields.ISO.dayOfWeek());
    }
}
