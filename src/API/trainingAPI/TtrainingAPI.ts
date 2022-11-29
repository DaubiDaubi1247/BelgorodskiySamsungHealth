export interface IsmallDataAboutTrainings {
    trainingArr : TrainingDataArr
}

export type TrainingDataArr = Array<ItrainigData>

export interface ItrainigData {
    id : number
    name : string
    countDays : number
}