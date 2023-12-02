package habits.backend.services;

import habits.backend.models.dto.CreateDayRequest;
import habits.backend.models.dto.DayPracticesResponse;
import habits.backend.models.dto.UpdateDayRequest;
import habits.backend.models.entities.DayPractices;
import habits.backend.models.entities.Habit;
import habits.backend.repository.DayPracticesRepository;
import habits.backend.repository.HabitRepository;
import habits.backend.utils.LocalDateUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DayPracticesService {

    private static final int DEFAULT_NUMBER_OF_DAYS_AGO = 70;

    private DayPracticesRepository dayPracticesRepository;

    private HabitRepository habitRepository;

    @Transactional
    public UUID create(CreateDayRequest createDayRequest) throws InvalidAttributeValueException {
        DayPractices dayPractices = DayPractices.builder()
                .createdAt(createDayRequest.createdAt())
                .build();
        List<UUID> habitIds = createDayRequest.habits();
        for (UUID habitId : habitIds) {
            Optional<Habit> habitOptional = habitRepository.findById(habitId);

            if (habitOptional.isPresent() && habitOptional.get().isPracticableAtDate(createDayRequest.createdAt()))
                dayPractices.add(habitOptional.get());
        }

        if (dayPractices.getHabits().isEmpty())
            throw new InvalidAttributeValueException("Any informed habit is exists or is valid for this date.");

        DayPractices createdDayPractices = dayPracticesRepository.save(dayPractices);

        return createdDayPractices.getId();
    }

    @Transactional
    public DayPracticesResponse getById(UUID id) {
        Optional<DayPractices> dayPracticeOptional = dayPracticesRepository.findById(id);

        if (dayPracticeOptional.isEmpty())
            return null;

        return DayPracticesResponse.from(dayPracticeOptional.get());
    }

    @Transactional
    public DayPracticesResponse update(UUID id, UpdateDayRequest updateDayRequest) {
        Optional<DayPractices> optionalDayPractices = dayPracticesRepository.findById(id);
        if (optionalDayPractices.isEmpty()) {
            return null;
        }

        DayPractices dayPractices = optionalDayPractices.get();

        List<UUID> habitIds = updateDayRequest.habits();
        for (Habit habit : dayPractices.getHabits()) {
            boolean containsId = habitIds.stream().anyMatch(uuid -> uuid.equals(habit.getId()));
            if (!containsId)
                dayPractices.remove(habit);
            else
                habitIds.remove(habit.getId());
        }

        for (UUID habitId : habitIds) {
           Optional<Habit> optionalHabit = habitRepository.findById(habitId);

           if (optionalHabit.isPresent() && optionalHabit.get().isPracticableAtDate(dayPractices.getCreatedAt()))
               dayPractices.add(optionalHabit.get());
        }

        return DayPracticesResponse.from(dayPracticesRepository.save(dayPractices));
    }

    public List<DayPracticesResponse> generateSummary() {
        LocalDate starts = LocalDateUtils.getDateOfDaysAgo(DEFAULT_NUMBER_OF_DAYS_AGO);

        List<DayPractices> foundDayPractices = dayPracticesRepository.findByCreatedAtAfter(starts);

        return foundDayPractices.stream()
                .map(DayPracticesResponse::from)
                .toList();
    }
}
