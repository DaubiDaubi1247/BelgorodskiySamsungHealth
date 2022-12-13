import { ArrDaysExpires, TrainingDataArr } from "../../API/trainingAPI/TtrainingAPI";
import { ItrainigData } from './../../API/trainingAPI/TtrainingAPI';


export interface ItrainitState {
    smallDataTrainings : TrainingDataArr,
    smallUserTraining :  ItrainigData | null
    arrDaysExpires : ArrDaysExpires,
    percentOfProgress : number
    messageForCreate : string
}

export enum CONST  {
    NO_DATA = -1
}