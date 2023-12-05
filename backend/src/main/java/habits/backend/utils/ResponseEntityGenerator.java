package habits.backend.utils;

import habits.backend.models.dto.ApiErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityGenerator {

    public static ResponseEntity<?> buildGenericBadRequest(String message, String detailedMessage) {
        Map<String, String> errors = new HashMap<>();
        errors.put("detail", detailedMessage);
        return ResponseEntity.badRequest().body(new ApiErrorResponse(message, errors));
    }
}
