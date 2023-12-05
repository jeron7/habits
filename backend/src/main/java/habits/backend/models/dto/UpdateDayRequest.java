package habits.backend.models.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record UpdateDayRequest(@NotEmpty List<UUID> habits) {}
