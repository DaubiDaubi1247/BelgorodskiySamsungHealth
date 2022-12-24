import axios from "axios";
import { Inputs } from "../../slices/auth/Types";
import { IResponse } from './API_T';

const authAxios = axios.create({
    baseURL: "http://localhost:8011/hole",
    
})

export const authAPI = {
    authUser(accessData : Inputs) {
        debugger
        return authAxios.post<IResponse>("/authentication", accessData)
    },

    registrationUser(accessData : Inputs) {
        return authAxios.post<IResponse>("/registration", accessData)
    }
}