export interface IsmallDataAboutTrainings {
    trainingArr : TrainingDataArr
}

export type TrainingDataArr = Array<ItrainigData>

export interface ItrainigData {
    id : number
    name : string
    countDays : number
}

export interface IDaysData {
    daysData : ArrDaysExpires
}

export type ArrDaysExpires = Array<DayExercises>

export type DayExercises = Array<Exercises>

type Exercises = {
    exercises : string
    countOfRepeats : number
}