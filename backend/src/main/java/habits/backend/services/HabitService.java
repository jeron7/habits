package habits.backend.services;

import habits.backend.models.dto.CreateHabitRequest;
import habits.backend.models.dto.HabitResponse;
import habits.backend.models.entities.DayOfWeek;
import habits.backend.models.entities.Habit;
import habits.backend.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HabitService {

    private HabitRepository habitRepository;

    public boolean exists(CreateHabitRequest request) {
        Optional<Habit> habitOptional = habitRepository.findByTitle(request.title());
        return habitOptional.isPresent();
    }

    public UUID create(CreateHabitRequest request) {
        Habit habit = Habit.builder()
                .title(request.title())
                .build();

        for (Integer isoDayOfWeek : request.daysOfWeek()) {
            DayOfWeek dayOfWeek = DayOfWeek.builder()
                    .isoDayOfWeek(isoDayOfWeek)
                    .build();
            habit.addDayOfWeek(dayOfWeek);
        }

        Habit createdHabit = habitRepository.save(habit);

        return createdHabit.getId();
    }

    @Transactional
    public HabitResponse getById(UUID id) {
        Optional<Habit> optionalHabit = habitRepository.findById(id);

        if (optionalHabit.isEmpty())
            return null;

        return HabitResponse.from(optionalHabit.get());
    }

    public List<HabitResponse> filterByDaysOfWeek(List<Integer> daysOfWeek) {
        List<Habit> habits = habitRepository.findByDaysOfWeek(daysOfWeek);

        return habits.stream()
                .map(HabitResponse::from)
                .toList();
    }

    public List<HabitResponse> findAll() {
        List<Habit> habits = habitRepository.findAll();

        return habits.stream()
                .map(HabitResponse::from)
                .toList();
    }
}
