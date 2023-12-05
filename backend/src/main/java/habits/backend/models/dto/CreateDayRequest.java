package habits.backend.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateDayRequest(
        @NotNull LocalDate createdAt,
        @NotEmpty List<UUID> habits
) {
}
