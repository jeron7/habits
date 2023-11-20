package habits.backend.models.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record HabitResponse(
        UUID id,
        String title,
        LocalDate createdAt,
        List<Integer> daysOfWeek
) {
}
