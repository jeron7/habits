package habits.backend.services;

import habits.backend.models.dto.ActivityResponse;
import habits.backend.models.dto.CreateActivityRequest;
import habits.backend.models.entities.Activity;
import habits.backend.models.entities.Habit;
import habits.backend.repository.ActivityRepository;
import habits.backend.repository.HabitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityService {

    private ActivityRepository activityRepository;

    private HabitRepository habitRepository;

    public UUID create(CreateActivityRequest createActivityRequest) throws InvalidAttributeValueException {
        Activity activity = Activity.builder()
                .createdAt(createActivityRequest.createdAt())
                .build();

        for (UUID habitId : createActivityRequest.habits()) {
            Optional<Habit> habitOptional = habitRepository.findById(habitId);
            if (habitOptional.isPresent())
                activity.addHabit(habitOptional.get());
        }

        if (activity.getHabits().isEmpty())
            throw new InvalidAttributeValueException("Any informed habit is valid.");

        Activity createdActivity = activityRepository.save(activity);

        return createdActivity.getId();
    }

    public List<ActivityResponse> getAllInRange(LocalDate fromDate, LocalDate toDate) {
        return null;
    }

    public ActivityResponse getById(UUID id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);

        if (activityOptional.isEmpty())
            return null;

        Activity activity = activityOptional.get();
        List<UUID> habitIds = activity.getHabits().stream()
                .map(Habit::getId)
                .toList();

        return ActivityResponse.builder()
                .id(activity.getId())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .habits(habitIds)
                .build();
    }
}
