import { ArrDaysExpires, TrainingDataArr } from "../../API/trainingAPI/TtrainingAPI";
import { ItrainigData } from '../../API/trainingAPI/TtrainingAPI';
import { IsmallDataAboutDiets, IsmallDataAboutDietsArr } from '../../API/dietsAPI/TdietsAPI';
import { TDishDescriptionArr } from "../../API/dishAPI/TdishAPI";


export interface IdishState {
    mealTimesArr : Array<string>
    mealTypesArr : Array<string>
}