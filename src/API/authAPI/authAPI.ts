import axios from "axios";
import { Inputs } from "../../components/auth/Auth";

const authAxios = axios.create({
    baseURL: "https://omgvamp-hearthstone-v1.p.rapidapi.com",
    withCredentials:true,
    
})

export const authAPI = {
    authUser(accessData : Inputs) {
        return authAxios.post("")
    },

    registrationUser(accessData : Inputs) {
        return authAxios.post("")
    }
}