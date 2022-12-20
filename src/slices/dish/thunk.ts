
import { createAsyncThunk } from '@reduxjs/toolkit';
import { IdishDescription } from '../../API/dishAPI/TdishAPI';

import { setLoading } from '../common/commonSlice';
import { dishAPI } from './../../API/dishAPI/dietsAPI';
import { IDishCreate } from './../../API/dishAPI/TdishAPI';


export const getMealsTimes = createAsyncThunk(
    "dish/getMealsTimes",
    async (_,thunkApi) => {
        try {
            const response = await dishAPI.getMealTimes();
            //thunkApi.dispatch(setLoading(false));
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

export const createDish = createAsyncThunk(
    "dish/createDish",
    async (body : IDishCreate, thunkApi) => {
        try {
            const response = await dishAPI.createDish(body);
            thunkApi.dispatch(setLoading(false));
            return response.data
        } catch (error) {debugger
            thunkApi.rejectWithValue(error)
        }
    }
)


