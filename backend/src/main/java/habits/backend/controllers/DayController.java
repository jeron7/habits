package habits.backend.controllers;

import habits.backend.models.dto.CreateDayRequest;
import habits.backend.models.dto.DayPracticesResponse;
import habits.backend.models.dto.UpdateDayRequest;
import habits.backend.services.DayPracticesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "/days")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DayController {

    private DayPracticesService dayPracticesService;

    @PostMapping(value = "/")
    ResponseEntity<?> create(@RequestBody @Valid CreateDayRequest createDayRequest) throws InvalidAttributeValueException {
        UUID createdId = dayPracticesService.create(createDayRequest);

        return ResponseEntity.created(fromCurrentRequest().path("/{id}").build(createdId)).build();
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateDayRequest updateDayRequest
    ) throws InvalidAttributeValueException {
        DayPracticesResponse updatedDayPractice = dayPracticesService.update(id, updateDayRequest);

        return ResponseEntity.ok(updatedDayPractice);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DayPracticesResponse> getById(@PathVariable UUID id) {
        DayPracticesResponse dayPracticesResponse = dayPracticesService.getById(id);

        if (Objects.isNull(dayPracticesResponse))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dayPracticesResponse);
    }

    @GetMapping("/summary/")
    ResponseEntity<?> getSummary() {
        List<DayPracticesResponse> activities = dayPracticesService.generateSummary();

        return ResponseEntity.ok(activities);
    }
}
