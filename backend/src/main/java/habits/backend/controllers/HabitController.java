package habits.backend.controllers;

import habits.backend.models.dto.CreateHabitRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

@RestController
@RequestMapping(value = "/habits")
public class HabitController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> create(@RequestBody @Valid CreateHabitRequest createHabitRequest) {
        return ResponseEntity.created(fromCurrentRequest().path("/{id}").build("random")).build();
    }
}
