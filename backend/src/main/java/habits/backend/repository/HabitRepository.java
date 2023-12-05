package habits.backend.repository;

import habits.backend.models.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {

    @Query("""
            SELECT habit
            FROM Habit habit
            JOIN FETCH habit.daysOfWeek
            WHERE habit.id = ANY(
                SELECT ha.id
                FROM Habit ha
                INNER JOIN ha.daysOfWeek dow
                WHERE dow.isoDayOfWeek in (?1)
            )
            """)
    List<Habit> findByDaysOfWeek(List<Integer> daysOfWeek);

    @Override
    @Query("""
            SELECT habit
            FROM Habit habit
            JOIN FETCH habit.daysOfWeek
            """)
    List<Habit> findAll();

    Optional<Habit> findByTitle(String title);
}
