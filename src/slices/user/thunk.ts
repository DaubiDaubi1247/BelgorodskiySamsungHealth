import { createAsyncThunk } from '@reduxjs/toolkit';
import { userAPI } from '../../API/userAPI/userAPI';
import { setLoading } from '../common/commonSlice';

export const getUserData = createAsyncThunk(
    "user/getUserData",
    async (id : number,thunkApi) => {
        try {
            const response = await userAPI.getUserData(id);
            thunkApi.dispatch(setLoading(false))
            return response.data
        } catch (error) {
            thunkApi.rejectWithValue("Что то пошло не так ...")
        }
    }
)
