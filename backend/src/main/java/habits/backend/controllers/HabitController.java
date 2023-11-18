package habits.backend.controllers;

import habits.backend.models.dto.ApiErrorResponse;
import habits.backend.models.dto.CreateHabitRequest;
import habits.backend.models.dto.HabitResponse;
import habits.backend.services.HabitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/habits")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HabitController {

    private HabitService habitService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createHabit(@RequestBody @Valid CreateHabitRequest createHabitRequest) {
        if (habitService.exists(createHabitRequest)) {
            Map<String, String> errors = new HashMap<>();
            errors.put("title", "habit with title " + createHabitRequest.title() + " already exists.");
            return ResponseEntity.badRequest().body(new ApiErrorResponse("Duplicated habit being requested.", errors));
        }

        UUID createdId = habitService.create(createHabitRequest);

        return ResponseEntity.created(fromCurrentRequest().path("/{id}").build(createdId)).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HabitResponse> getHabit(@PathVariable UUID id) {
        HabitResponse habitResponse = habitService.getById(id);

        if (Objects.isNull(habitResponse))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(habitResponse);
    }
}
