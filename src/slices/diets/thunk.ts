
import { createAsyncThunk } from '@reduxjs/toolkit';

import { setLoading } from '../common/commonSlice';

import { dietsAPI } from './../../API/dietsAPI/dietsAPI';

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
