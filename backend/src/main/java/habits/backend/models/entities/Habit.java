package habits.backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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

    @Column(nullable = false, unique = true)
    private String title;

    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany(
        mappedBy = "habit",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<DayOfWeek> daysOfWeek;

    @ManyToMany(mappedBy = "habits")
    private Set<Activity> activities;

    @Builder
    public Habit(UUID id, String title) {
        this.id = id;
        this.title = title;
        this.daysOfWeek = new HashSet<>();
        this.activities = new HashSet<>();
    }

    public void addDayOfWeek(DayOfWeek dayOfWeek) {
        this.daysOfWeek.add(dayOfWeek);
        dayOfWeek.setHabit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habit habit = (Habit) o;
        return Objects.equals(getId(), habit.getId()) && Objects.equals(getTitle(), habit.getTitle()) && Objects.equals(getCreatedAt(), habit.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCreatedAt());
    }
}
