
import { createAsyncThunk } from '@reduxjs/toolkit';

import { setLoading } from '../common/commonSlice';
import { dishAPI } from './../../API/dishAPI/dietsAPI';


export const getMealsTimes = createAsyncThunk(
    "dish/getMealsTimes",
    async (_,thunkApi) => {
        try {
            const response = await dishAPI.getMealTimes();
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const getTypes = createAsyncThunk(
    "dish/getTypes",
    async (_,thunkApi) => {
        try {
            const response = await dishAPI.getTypes();
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)
