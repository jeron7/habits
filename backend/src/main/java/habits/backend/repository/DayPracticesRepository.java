package habits.backend.repository;

import habits.backend.models.entities.DayPractices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DayPracticesRepository extends JpaRepository<DayPractices, UUID> {

    @Query("""
            SELECT dp
            FROM DayPractices dp
            LEFT JOIN FETCH dp.habits ha
            WHERE dp.createdAt >= ?1            
            """)
    List<DayPractices> findByCreatedAtAfter(LocalDate starts);

}
