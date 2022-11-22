
import { createAsyncThunk } from '@reduxjs/toolkit';

import { authAPI } from './../../API/authAPI/authAPI';
import { Inputs } from './Types';

export const registrationUser = createAsyncThunk(
    "auth/registrationUser",
    async (accessData : Inputs,thunkApi) => {
        try {
            const response = await authAPI.registrationUser(accessData)
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const authUser = createAsyncThunk(
    "auth/authUser",
    async (accessData : Inputs,thunkApi) => {
        try {
            const response = await authAPI.authUser(accessData)
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)