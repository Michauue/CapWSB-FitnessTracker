package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    @Autowired
    private  TrainingServiceImpl trainingService;
    @Autowired
    private  TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingService.createTraining(trainingDto);
        return trainingMapper.toDto(training);
    }

    @GetMapping("/endedBefore")
    public List<TrainingDto> getTrainingsByEndTimeBefore(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.getTrainingsByEndTimeBefore(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activity/{activityType}")
    public List<TrainingDto> getTrainingsByActivityType(@PathVariable String activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        Training training = trainingService.updateTraining(id, trainingDto);
        return trainingMapper.toDto(training);
    }
}
