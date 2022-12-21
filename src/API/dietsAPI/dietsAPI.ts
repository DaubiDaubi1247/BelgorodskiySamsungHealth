import axios from "axios"
import {TDishDescriptionArr } from "../dishAPI/TdishAPI"
import { Ifilters, IsmallDataAboutDiets, IsmallDataAboutDietsArr, TCreateDush} from "./TdietsAPI"


const dietsAxios = axios.create({
    baseURL: "http://localhost:8010/diet",
})

export const dietsAPI = {
    getSmallDataAboutDiets() {
        return dietsAxios.get<IsmallDataAboutDietsArr>("/getAll?status=available")
    },
    getSmallDataAboutUserDiet(userId : number) {
        return axios.get<IsmallDataAboutDietsArr>(`http://localhost:8010/user/getDiet?userId=${userId}`)
    },

    getMealsByFilter(filters : Ifilters) {
        return dietsAxios.get<TDishDescriptionArr>(`/dishes?idDiet=${filters.dietId}&typeOfMeal=${filters.typeOfMeal}&MailTime=${filters.mealTime}`)
    },

    changeStatusDiet(dietId : number) {
        return dietsAxios.get(`/changeStatus?id=${dietId}`)
    },
    createDiet(body : TCreateDush) {
        return dietsAxios.post(`/add`, body)
    } 

}