import axios from "axios"
import { IDaysData, IsmallDataAboutTrainings, ItrainigData } from "./TtrainingAPI"


const trainingAxios = axios.create({
    baseURL: "http://localhost:8010",
})

export const trainingAPI = {
    getSmallDataAboutTrainings() {
        return trainingAxios.get<IsmallDataAboutTrainings>("")
    },

    getUserTraining(id : number) {
        return trainingAxios.get<ItrainigData>(`${id}`)
    },

    getDataDaysExpires(id : number) {
        return trainingAxios.get<IDaysData>(`${id}`)
    }

    // registrationUser(accessData : Inputs) {
    //     return trainingAxios.post<IResponse>("/hole/registration", accessData)
    // }
}