import axios from "axios"
import { IuserDataForSet } from "./TuserAPI"


const userAxios = axios.create({
    baseURL: "http://localhost:8010/user",
})

export const userAPI = {
    setUserTrain(userId : number, trainId : number) {
        return userAxios.get(`setTrainToUser?userId=${userId}&trainId=${trainId}`)
    },
    getUserData(userId : number ){
        return userAxios.get(`/getUserData?userId=${userId}`)
    },
    setUserData(userData : IuserDataForSet){
        return userAxios.post("/setUserData", userData)
    } 
}
