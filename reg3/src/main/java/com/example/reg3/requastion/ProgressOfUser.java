package com.example.reg3.requastion;

import org.springframework.beans.factory.annotation.Value;

public interface ProgressOfUser {

    Long getId();

    String getLabel();

    Integer getCountOfDays();

    String getDescription();

    Integer getDayOfTraining();

}
