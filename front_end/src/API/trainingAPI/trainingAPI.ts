import axios from "axios"
import { IDaysData, IsmallDataAboutTrainings, ItrainigData, TCreateTrainig, TrainingDataArr } from "./TtrainingAPI"


const trainingAxios = axios.create({
    baseURL: "http://79.143.30.176:8011/training",
})

export const trainingAPI = {
    getSmallDataAboutTrainings() {
        return trainingAxios.get<TrainingDataArr>("/LightBackground")
    },

    getUserTraining(id : number) {
        return trainingAxios.get<ItrainigData>(`/userTrainingProgress?id=${id}`)
    },

    getDataDaysExpires(trainingId : number) {
        return trainingAxios.get<IDaysData>(`/daysOfTrain?id=${trainingId}`)
    },

    createTraining(trainig : TCreateTrainig) {
        return trainingAxios.post("/addTrain", trainig)
    },
    
    deactivateTraining(trainingId : number) {
        return trainingAxios.get(`/deactivate?trainId=${trainingId}`)
    }

}