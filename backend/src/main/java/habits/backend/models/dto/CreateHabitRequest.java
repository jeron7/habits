package habits.backend.models.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public record CreateHabitRequest(
        @NotNull @NotBlank String title,
        @UniqueElements @NotNull @Size(min = 1, max = 7) List<@Min(1) @Max(7) Integer> daysOfWeek
) {
}
