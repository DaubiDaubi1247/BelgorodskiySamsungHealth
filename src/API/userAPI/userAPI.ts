import axios from "axios"


const userAxios = axios.create({
    baseURL: "http://localhost:8010/user",
})

export const userAPI = {
    setUserTrain(userId : number, trainId : number) {
        return userAxios.get(`setTrainToUser?userId=${userId}&trainId=${trainId}`)
    }
}

