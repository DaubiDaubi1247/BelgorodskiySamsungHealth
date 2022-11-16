
import { createAsyncThunk } from '@reduxjs/toolkit';
import { Inputs } from '../../components/auth/Auth';
import { authAPI } from './../../API/authAPI/authAPI';

export const registrationUser = createAsyncThunk(
    "auth/registrationUser",
    async (accessData : Inputs,thunkApi) => {
        try {
            debugger
            const response = await authAPI.registrationUser(accessData)
            return response.data.freeGames
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

export const authUser = createAsyncThunk(
    "auth/authUser",
    async (accessData : Inputs,thunkApi) => {
        try {
            debugger
            const response = await authAPI.authUser(accessData)
            return response.data.freeGames
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)