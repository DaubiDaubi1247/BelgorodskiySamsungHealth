import { TrainingDataArr } from "../../API/trainingAPI/TtrainingAPI";
import { ItrainigData } from './../../API/trainingAPI/TtrainingAPI';


export interface ItrainitState {
    smallDataTrainings : TrainingDataArr,
    smallUserTraining :  ItrainigData | null
    today : number
}

export enum CONST  {
    NO_DATA = -1
}