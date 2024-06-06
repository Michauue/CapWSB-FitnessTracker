package com.capgemini.wsb.fitnesstracker.notification;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@EnableScheduling
@EnableAsync
public class TrainingRaportGenerator {

    @Autowired
    private TrainingProvider trainingProvider;
    @Autowired
    private EmailSender emailSender;
    @Scheduled(cron = "0 0 6 * * MON") // Wysyłka w każdy poniedziałek o 6:00
    @Async
    public void generateReport() {
        List<Training> Trainings = trainingProvider.getAllTrainings();
        Map< User, List<Training>> userTraining = Trainings.stream().collect(Collectors.groupingBy(Training::getUser));
        userTraining.forEach((user, trainings) -> {
            long trainingsCount = trainings.size();
            String trainingsString = "";
            if (trainingsCount == 1){
                trainingsString = " training";
            } else {
                trainingsString = " trainings";
            }
            String result = (user.getFirstName() + ", good job - You have done " + trainingsCount + trainingsString + "!");
            emailSender.send(new EmailDto(user.getEmail(), "Training report", result));
        });
    }

}
