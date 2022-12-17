import axios from "axios"
import { TDishDescriptionArr } from "../dishAPI/TdishAPI"
import { Ifilters, IsmallDataAboutDiets, IsmallDataAboutDietsArr} from "./TdietsAPI"


const dietsAxios = axios.create({
    baseURL: "http://localhost:8010/diet",
})

export const dietsAPI = {
    getSmallDataAboutDiets() {
        return dietsAxios.get<IsmallDataAboutDietsArr>("/getAll?status=available")
    },
    getMealsByFilter(filters : Ifilters) {
        return dietsAxios.get<TDishDescriptionArr>(`/dishes?idDiet=${filters.dietId}&typeOfMeal=${filters.typeOfMeal}&MailTime=${filters.mealTime}`)
    }

}