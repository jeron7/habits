package habits.backend.models.dto;

import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Builder
public record HabitResponse(
        UUID id,
        String title,
        Timestamp createdAt,
        List<Integer> daysOfWeek
) {
}
