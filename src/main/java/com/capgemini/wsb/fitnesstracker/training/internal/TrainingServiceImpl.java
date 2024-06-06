package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO: Provide Impl
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Training> getTraining(Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public Training createTraining(TrainingDto trainingDto) {
        User user = userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(trainingDto.getUserId()));
        Training training = new TrainingMapper().toEntity(trainingDto, user);
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getTrainingsByEndTimeBefore(Date date) {
        return trainingRepository.findByEndTimeBefore(date);
    }

    @Override
    public List<Training> getTrainingsByActivityType(String activityType) {
        return trainingRepository.findByActivityType(ActivityType.valueOf(activityType.toUpperCase()));
    }

    @Override
    public Training updateTraining(Long id, TrainingDto trainingDto) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));
        existingTraining.setDistance(trainingDto.getDistance());
        return trainingRepository.save(existingTraining);
    }
}
