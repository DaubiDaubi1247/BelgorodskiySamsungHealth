import axios from "axios"
import { IDishCreate, IsmallDataAboutDiets, IsmallDataAboutDietsArr} from "./TdishAPI"


const dishAxios = axios.create({
    baseURL: "http://app:8011/dish",
})

export const dishAPI = {
    getMealTimes() {
        return dishAxios.get<Array<string>>("/getMealTimes")
    },
    getTypes() {
        return dishAxios.get<Array<string>>("/getTypes")
    },
    createDish(body : IDishCreate) {
        return dishAxios.post("/add" , body)
    }

}