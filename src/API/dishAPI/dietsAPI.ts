import axios from "axios"
import { IsmallDataAboutDiets, IsmallDataAboutDietsArr} from "./TdishAPI"


const dishAxios = axios.create({
    baseURL: "http://localhost:8010/dish",
})

export const dishAPI = {
    getMealTimes() {
        return dishAxios.get<Array<string>>("/getMealTimes")
    },
    getTypes() {
        return dishAxios.get<Array<string>>("/getTypes")
    },

}