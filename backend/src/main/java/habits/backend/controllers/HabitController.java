package habits.backend.controllers;

import habits.backend.models.dto.CreateHabitRequest;
import habits.backend.models.dto.HabitResponse;
import habits.backend.services.HabitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static habits.backend.utils.ResponseEntityGenerator.buildGenericBadRequest;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "/habits")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HabitController {

    private HabitService habitService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> create(@RequestBody @Valid CreateHabitRequest createHabitRequest) {
        if (habitService.exists(createHabitRequest)) {
            return buildGenericBadRequest(
                    "Duplicated habit being requested.",
                    "habit with title " + createHabitRequest.title() + " already exists."
            );
        }

        UUID createdId = habitService.create(createHabitRequest);

        return ResponseEntity.created(fromCurrentRequest().path("/{id}").build(createdId)).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HabitResponse> getById(@PathVariable UUID id) {
        HabitResponse habitResponse = habitService.getById(id);

        if (Objects.isNull(habitResponse))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(habitResponse);
    }


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HabitResponse>> getAll(
            @RequestParam(required = false) @UniqueElements @Size(min = 1, max = 7) List<@Min(1) @Max(7) Integer> daysOfWeek
    ) {
        if (Objects.isNull(daysOfWeek)) {
            return ResponseEntity.ok(habitService.findAll());
        } else {
            return ResponseEntity.ok(habitService.filterByDaysOfWeek(daysOfWeek));
        }
    }
}
