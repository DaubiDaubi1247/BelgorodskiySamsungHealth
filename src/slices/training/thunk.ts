
import { createAsyncThunk } from '@reduxjs/toolkit';
import { setLoading } from '../common/commonSlice';
import { trainingAPI } from './../../API/trainingAPI/trainingAPI';

export const getSmallDataAboutTrainings = createAsyncThunk(
    "training/getSmallDataAboutTrainings",
    async (_,thunkApi) => {
        try {
            const response = await trainingAPI.getSmallDataAboutTrainings();
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const getUserTraining = createAsyncThunk(
    "training/getUserTraining",
    async (id : number,thunkApi) => {
        try {
            const response = await trainingAPI.getUserTraining(id)
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.dispatch(setLoading(false)); // ---- пока нет сервера прийдется так тестить

            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const getArrDaysExpires = createAsyncThunk(
    "training/getArrDaysExpires",
   async (id : number, thunkApi) => {
        try {
            const response = await trainingAPI.getDataDaysExpires(id);
            thunkApi.dispatch(setLoading(false))
            return response.data
        } catch (error) {
            thunkApi.dispatch(setLoading(false)); // ---- пока нет сервера прийдется так тестить

            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
   }
)