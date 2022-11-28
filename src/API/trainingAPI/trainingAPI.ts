import axios from "axios"
import { IsmallDataAboutTrainings, ItrainigData } from "./TtrainingAPI"


const authAxios = axios.create({
    baseURL: "http://localhost:8010",
})

export const trainingAPI = {
    getSmallDataAboutTrainings() {
        return authAxios.get<IsmallDataAboutTrainings>("")
    },

    // registrationUser(accessData : Inputs) {
    //     return authAxios.post<IResponse>("/hole/registration", accessData)
    // }
}