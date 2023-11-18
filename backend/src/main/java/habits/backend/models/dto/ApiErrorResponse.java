package habits.backend.models.dto;

import lombok.Builder;

import java.util.Map;
@Builder
public record ApiErrorResponse(
        String message,
        Map<String, String> errors
) {
}
