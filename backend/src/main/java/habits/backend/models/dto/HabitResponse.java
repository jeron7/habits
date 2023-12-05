package habits.backend.models.dto;

import habits.backend.models.entities.DayOfWeek;
import habits.backend.models.entities.DayPractices;
import habits.backend.models.entities.Habit;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record HabitResponse(UUID id, String title, LocalDate createdAt, List<Integer> daysOfWeek) {
    public static HabitResponse from(Habit habit) {
        List<Integer> daysOfWeek = habit.getDaysOfWeek().stream()
                .map(DayOfWeek::getIsoDayOfWeek)
                .toList();

        return HabitResponse.builder()
                .id(habit.getId())
                .title(habit.getTitle())
                .createdAt(habit.getCreatedAt())
                .daysOfWeek(daysOfWeek)
                .build();
    }
}
