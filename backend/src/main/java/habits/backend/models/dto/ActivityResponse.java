package habits.backend.models.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record ActivityResponse(
        UUID id,
        LocalDate createdAt,
        LocalDate updatedAt,
        List<UUID> habits
) {
}
