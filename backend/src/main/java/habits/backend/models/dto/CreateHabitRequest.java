package habits.backend.models.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateHabitRequest(
        @NotBlank(message = "Not accepts blank values for title.") String title,
        @Size(min = 1, max = 7) List<@Min(1) @Max(7) Integer> isoDaysOfWeek
) {
}
