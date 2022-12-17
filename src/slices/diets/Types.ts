import { ArrDaysExpires, TrainingDataArr } from "../../API/trainingAPI/TtrainingAPI";
import { ItrainigData } from './../../API/trainingAPI/TtrainingAPI';
import { IsmallDataAboutDiets, IsmallDataAboutDietsArr } from './../../API/dietsAPI/TdietsAPI';


export interface IdietsState {
    smallDataAboutDiets : IsmallDataAboutDietsArr
    smallDataAboutUserDiet : IsmallDataAboutDiets | null
    userHasDiet : boolean
    currentDietId : IsmallDataAboutDiets | null
}