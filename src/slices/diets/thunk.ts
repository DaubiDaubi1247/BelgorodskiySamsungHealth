
import { createAsyncThunk } from '@reduxjs/toolkit';
import { Ifilters, TCreateDush } from '../../API/dietsAPI/TdietsAPI';

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
            console.log(response)
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const getSmallDataAboutUserDiet = createAsyncThunk(
    "diets/getSmallDataAboutUserDiet",
    async (userId : number,thunkApi) => {
        try {
            const response = await dietsAPI.getSmallDataAboutUserDiet(userId);
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

export const getMealsByFilter = createAsyncThunk(
    "diets/getMealsByFilter",
   async (data : Ifilters, thunkApi) => {
        try {
            const response = await dietsAPI.getMealsByFilter(data)
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            return thunkApi.rejectWithValue("Такие блюда отсутсвуют")
        }
   }
)

export const createDiet = createAsyncThunk(
    "diets/createDiet",
   async (body : TCreateDush, thunkApi) => {
        try {
            const response = await dietsAPI.createDiet(body)
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue(error)
        }
   }
)



