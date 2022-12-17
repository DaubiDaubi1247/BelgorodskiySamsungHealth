
import { createAsyncThunk } from '@reduxjs/toolkit';

import { setLoading } from '../common/commonSlice';

import { dietsAPI } from './../../API/dietsAPI/dietsAPI';
import { userAPI } from './../../API/userAPI/userAPI';

type TSetUserDiet = {
    userId : number
    dietId : number
}

export const getSmallDataAboutDiets = createAsyncThunk(
    "diets/getSmallDataAboutDiets",
    async (_,thunkApi) => {
        try {
            const response = await dietsAPI.getSmallDataAboutDiets();
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const setUserDiet = createAsyncThunk(
    "diets/setUserDiet",
   async (data : TSetUserDiet, thunkApi) => {
        try {
            const response = await userAPI.setUserDiet(data.userId, data.dietId)
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("...")
        }
   }
)

export const getSmallDataUserTraing = createAsyncThunk(
    "diets/getSmallDataUserTraing",
   async (userId : number, thunkApi) => {
        try {
            const response = await dietsAPI.getSmallDataUserTraing(userId)
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("...")
        }
   }
)
