package habits.backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private Timestamp createdAt;

    @OneToMany(
        mappedBy = "habit",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<DayOfWeek> daysOfWeek;

    @Builder
    public Habit(UUID id, String title, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.daysOfWeek = new HashSet<>();
    }

    public void addDayOfWeek(DayOfWeek dayOfWeek) {
        this.daysOfWeek.add(dayOfWeek);
        dayOfWeek.setHabit(this);
    }
}
