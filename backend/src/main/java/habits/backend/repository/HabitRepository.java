package habits.backend.repository;

import habits.backend.models.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {

    Optional<Habit> findByTitle(String title);
}
