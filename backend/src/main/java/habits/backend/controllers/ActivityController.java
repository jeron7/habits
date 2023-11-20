package habits.backend.controllers;

import habits.backend.models.dto.ActivityResponse;
import habits.backend.models.dto.CreateActivityRequest;
import habits.backend.models.dto.HabitResponse;
import habits.backend.services.ActivityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static habits.backend.utils.ResponseEntityGenerator.buildGenericBadRequest;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/activities")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityController {

    private ActivityService activityService;

    @PostMapping(value = "/")
    ResponseEntity<?> create(@RequestBody @Valid CreateActivityRequest createActivityRequest) throws InvalidAttributeValueException {
        UUID createdId = activityService.create(createActivityRequest);

        return ResponseEntity.created(fromCurrentRequest().path("/{id}").build(createdId)).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActivityResponse> getById(@PathVariable UUID id) {
        ActivityResponse activityResponse = activityService.getById(id);

        if (Objects.isNull(activityResponse))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(activityResponse);
    }

    @GetMapping("/")
    ResponseEntity<?> getAllInRange(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
        if (fromDate.isBefore(toDate)) {
            return buildGenericBadRequest(
                    "Invalid range of date.",
                    "fromDate should be after toDate value.");
        }

        List<ActivityResponse> activities = activityService.getAllInRange(fromDate, toDate);

        return ResponseEntity.ok(activities);
    }
}
