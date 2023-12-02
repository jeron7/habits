package habits.backend.models.dto;

import habits.backend.models.entities.DayPractices;
import habits.backend.models.entities.Habit;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record DayPracticesResponse(UUID id, LocalDate createdAt, LocalDate updatedAt, List<UUID> habits) {

    public static DayPracticesResponse from(DayPractices dayPractices) {
        List<UUID> habitIds = dayPractices.getHabits().stream()
                .map(Habit::getId)
                .toList();

        return DayPracticesResponse.builder()
                .id(dayPractices.getId())
                .createdAt(dayPractices.getCreatedAt())
                .updatedAt(dayPractices.getUpdatedAt())
                .habits(habitIds)
                .build();
    }
}
