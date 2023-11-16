package habits.backend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CreateHabitRequest(
        @NotBlank(message = "Not accepts blank values for title.") String title,
        List<
            @NotNull
            @Pattern(regexp = "[1-7]", message = "Follow ISO-8601 specification for day of week.") Integer
            > isoDaysOfWeek
) {
}
