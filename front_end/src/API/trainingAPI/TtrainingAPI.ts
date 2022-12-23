export interface IsmallDataAboutTrainings {
    trainingArr : TrainingDataArr
}

export type TrainingDataArr = Array<ItrainigData>

export interface ItrainigData {
    id : number
    label : string
    countOfDays : number
    description : string
    dayOfTraining? : number
    presentOfProgress? : number
}

export interface ItrainigDataForCreate {
    label : string
    countOfDays : number
    description : string
    presentOfProgress? : number
}

export interface IDaysData {
    daysOfTrainings : ArrDaysExpires
}

export type ArrDaysExpires = Array<IdayDescription>

export type DayExercises = Array<Exercises>

export interface IdayDescription {
    numberOfDay : number
    sets : DayExercises
}

export type Exercises = {
    restTimeInSec : string
    numberOfRepetitions : number
    exercise : {
        label : string
        description : string
    }
}

export type TCreateTrainig = ItrainigDataForCreate | IDaysData