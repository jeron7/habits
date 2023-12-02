package habits.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import habits.backend.utils.LocalDateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Habit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @CreationTimestamp
    private LocalDate createdAt;

    @JsonManagedReference
    @OneToMany(
        mappedBy = "habit",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<DayOfWeek> daysOfWeek;

    @ManyToMany(mappedBy = "habits")
    private Set<DayPractices> dayPractices;

    @Builder
    public Habit(UUID id, String title) {
        this.id = id;
        this.title = title;
        this.daysOfWeek = new HashSet<>();
        this.dayPractices = new HashSet<>();
    }

    public void addDayOfWeek(DayOfWeek dayOfWeek) {
        this.daysOfWeek.add(dayOfWeek);
        dayOfWeek.setHabit(this);
    }

    public boolean isPracticableAtDate(LocalDate date) {
        int createAtDayOfWeek = LocalDateUtils.getDayOfWeek(date);
        return this.getDaysOfWeek().stream()
                .anyMatch(dayOfWeek -> dayOfWeek.getIsoDayOfWeek() == createAtDayOfWeek);
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
