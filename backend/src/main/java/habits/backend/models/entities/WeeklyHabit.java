package habits.backend.models.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class WeeklyHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
        Day of week representation following ISO-8601 specification.
     */
    @Column(nullable = false, unique = true)
    private int isoDayOfWeek;

    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;
}
