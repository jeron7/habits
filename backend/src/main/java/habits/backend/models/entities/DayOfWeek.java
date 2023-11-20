package habits.backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /*
        Day of week representation following ISO-8601 specification.
     */
    @Column(nullable = false)
    private int isoDayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    private Habit habit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayOfWeek dayOfWeek = (DayOfWeek) o;
        return getIsoDayOfWeek() == dayOfWeek.getIsoDayOfWeek() && Objects.equals(getId(), dayOfWeek.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIsoDayOfWeek());
    }
}
