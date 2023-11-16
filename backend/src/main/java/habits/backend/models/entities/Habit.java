package habits.backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    private Set<WeeklyHabit> daysOfWeek;

    @ManyToMany(mappedBy = "activities")
    private Collection<Activity> activities;

}
