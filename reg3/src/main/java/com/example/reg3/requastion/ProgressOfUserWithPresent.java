package com.example.reg3.requastion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgressOfUserWithPresent {

    Long id;

    String label;

    Integer countOfDays;

    String description;
    @JsonIgnore
    Integer dayOfTraining;

    Long  presentOfProgress;

    public ProgressOfUserWithPresent(ProgressOfUser progress) {
        id = progress.getId();
        label = progress.getLabel();
        countOfDays = progress.getCountOfDays();
        description = progress.getDescription();
        dayOfTraining = progress.getDayOfTraining();
        presentOfProgress =  Math.round(((countOfDays) * 1. - 1) / dayOfTraining * 100);
    }
}
