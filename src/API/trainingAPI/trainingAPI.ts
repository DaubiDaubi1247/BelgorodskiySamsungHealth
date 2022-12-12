import axios from "axios"
import { IDaysData, IsmallDataAboutTrainings, ItrainigData, TCreateTrainig, TrainingDataArr } from "./TtrainingAPI"


const trainingAxios = axios.create({
    baseURL: "http://localhost:8010/training",
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
    }

}