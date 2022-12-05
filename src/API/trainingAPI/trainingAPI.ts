import axios from "axios"
import { IsmallDataAboutTrainings, ItrainigData } from "./TtrainingAPI"


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

    getFullDataAboutTrainings(id : number) {
        //return trainingAxios.get<>(`${id}`)
    }

    // registrationUser(accessData : Inputs) {
    //     return trainingAxios.post<IResponse>("/hole/registration", accessData)
    // }
}