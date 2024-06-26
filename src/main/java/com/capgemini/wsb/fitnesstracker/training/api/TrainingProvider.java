package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    List<Training> getAllTrainings();

    List<Training> getTrainingsByUserId(Long userId);

    Training createTraining(TrainingDto trainingDto);

    List<Training> getTrainingsByEndTimeBefore(Date date);

    List<Training> getTrainingsByActivityType(String activityType);

    Training updateTraining(Long id, TrainingDto trainingDto);
}
