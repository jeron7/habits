package habits.backend.models.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class DayPractices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "habit_id")
    )
    private Set<Habit> habits;

    @Builder
    public DayPractices(UUID id, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.habits = new HashSet<>();
    }

    public boolean containsHabit(UUID habitId) {
        return habits.stream()
                .anyMatch(habit -> habit.getId().equals(habitId));
    }

    public void add(Habit habit) {
        this.habits.add(habit);
        habit.getDayPractices().add(this);
    }

    public void remove(Habit habit) {
        this.habits.remove(habit);
        habit.getDayPractices().remove(this);
    }
}
