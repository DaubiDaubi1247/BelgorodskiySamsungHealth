
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { IsmallDataAboutDietsArr } from '../../API/dietsAPI/TdietsAPI';
import { IdietsState } from './Types';
import { getSmallDataAboutDiets } from './thunk';

const initialState : IdietsState = {
    smallDataAboutDiets : []
}

const dietsSlice = createSlice({
    name : "diets",
    initialState,
    reducers: {

    },

    extraReducers: builder => {
        builder
            .addCase(getSmallDataAboutDiets.fulfilled.type, (state, action: PayloadAction<IsmallDataAboutDietsArr>) => {
                state.smallDataAboutDiets = action.payload
            })
    }
})

export default dietsSlice.reducer