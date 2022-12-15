import axios from "axios"
import { IsmallDataAboutDietsArr} from "./TdietsAPI"


const dietsAxios = axios.create({
    baseURL: "http://localhost:8011/diet",
})

export const dietsAPI = {
    getSmallDataAboutDiets() {
        return dietsAxios.get<IsmallDataAboutDietsArr>("/getAll")
    },

}