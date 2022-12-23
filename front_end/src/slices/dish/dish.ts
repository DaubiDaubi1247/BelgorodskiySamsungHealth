
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { IsmallDataAboutDietsArr } from '../../API/dietsAPI/TdietsAPI';
import { IdishState } from './Types';
import { getMealsTimes, getTypes, createDish } from './thunk';

const initialState : IdishState = {
    mealTimesArr : [],
    mealTypesArr : [],
    mealCreateRes : ""
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
                debugger
                state.mealTypesArr = action.payload
            })
            .addCase(createDish.rejected.type, (state, action: PayloadAction<string>) => {
            })
            .addCase(createDish.fulfilled.type, (state, action: PayloadAction<string>) => {
                
                state.mealCreateRes = action.payload
            })
    }
})

export const {} = dishSlice.actions

export default dishSlice.reducer