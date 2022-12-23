import axios from "axios"
import { IuserDataForSet } from "./TuserAPI"


const userAxios = axios.create({
    baseURL: "http://app:8011/user",
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
    },
    updateDayUserTraining(userId : number) {
        return userAxios.get(`/updayteDayOfTrain?userId=${userId}`)
    },
    setUserDiet(userId : number, dietId : number) {
        return userAxios.get(`setDietToUser?userId=${userId}&dietId=${dietId}`)
    }
}

