
import { createAsyncThunk } from '@reduxjs/toolkit';
import { DataForSetTrain } from '../../API/userAPI/TuserAPI';
import { userAPI } from '../../API/userAPI/userAPI';
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
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
   }
)

export const setUserTrain = createAsyncThunk(
    "training/setUserTrain",
   async (body : DataForSetTrain, thunkApi) => {
        try {
            const response = await userAPI.setUserTrain(body.userId, body.trainId);
            thunkApi.dispatch(setLoading(false))
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
   }
)