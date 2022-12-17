
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { IsmallDataAboutDietsArr } from '../../API/dietsAPI/TdietsAPI';
import { IdishState } from './Types';
import {getMealsTimes, getTypes } from './thunk';

const initialState : IdishState = {
    mealTimesArr : [],
    mealTypesArr : []
}

const dishSlice = createSlice({
    name : "dish",
    initialState,
    reducers: {
        
    },

    extraReducers: builder => {
        builder
            .addCase(getMealsTimes.fulfilled.type, (state, action: PayloadAction<Array<string>>) => {
                state.mealTimesArr = action.payload
            })
            .addCase(getTypes.fulfilled.type, (state, action: PayloadAction<Array<string>>) => {
                state.mealTypesArr = action.payload
            })
    }
})

export const {} = dishSlice.actions

export default dishSlice.reducer