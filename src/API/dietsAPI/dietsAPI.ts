import axios from "axios"
import { IsmallDataAboutDiets, IsmallDataAboutDietsArr} from "./TdietsAPI"


const dietsAxios = axios.create({
    baseURL: "http://localhost:8010/diet",
})

export const dietsAPI = {
    getSmallDataAboutDiets() {
        return dietsAxios.get<IsmallDataAboutDietsArr>("/getAll")
    },
    getSmallDataUserTraing(userId : number) {
        return dietsAxios.get<IsmallDataAboutDiets>(``)
    }

}