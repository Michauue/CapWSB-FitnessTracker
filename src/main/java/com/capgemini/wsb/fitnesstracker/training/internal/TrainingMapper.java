package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDto toDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setUserId(training.getUser().getId());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setActivityType(training.getActivityType().getDisplayName());
        dto.setDistance(training.getDistance());
        dto.setAverageSpeed(training.getAverageSpeed());
        return dto;
    }

    public Training toEntity(TrainingDto dto, User user) {
        return new Training(
                user,
                dto.getStartTime(),
                dto.getEndTime(),
                ActivityType.valueOf(dto.getActivityType().toUpperCase()),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
    }
}
