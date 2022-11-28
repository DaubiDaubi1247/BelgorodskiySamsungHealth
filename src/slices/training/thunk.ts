
import { createAsyncThunk } from '@reduxjs/toolkit';
import { trainingAPI } from './../../API/trainingAPI/trainingAPI';

export const getSmallDataAboutTrainings = createAsyncThunk(
    "training/getSmallDataAboutTrainings",
    async (_,thunkApi) => {
        try {
            const response = await trainingAPI.getSmallDataAboutTrainings();
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)

// export const authUser = createAsyncThunk(
//     "auth/authUser",
//     async (accessData : Inputs,thunkApi) => {
//         try {
//             const response = await authAPI.authUser(accessData)
//             return response.data
//         } catch (error) {
//             thunkApi.rejectWithValue("Что то пошло не так ...")
//         }
//     }
// )