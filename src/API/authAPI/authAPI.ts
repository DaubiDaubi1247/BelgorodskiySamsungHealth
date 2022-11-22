import axios from "axios";
import { Inputs } from "../../slices/auth/Types";
import { IResponse } from './API_T';

const authAxios = axios.create({
    baseURL: "http://localhost:8010",
    
})

export const authAPI = {
    authUser(accessData : Inputs) {
        return authAxios.post<IResponse>("/hole/authentication", accessData)
    },

    registrationUser(accessData : Inputs) {
        return authAxios.post<IResponse>("/hole/registration", accessData)
    }
}