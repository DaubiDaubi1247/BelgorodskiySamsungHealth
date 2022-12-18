
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { IsmallDataAboutDietsArr } from '../../API/dietsAPI/TdietsAPI';
import { IdietsState } from './Types';
import { getSmallDataAboutDiets, setUserDiet, getMealsByFilter } from './thunk';
import { IsmallDataAboutDiets } from './../../API/dietsAPI/TdietsAPI';
import { stat } from 'fs';
import { CONST } from '../common/Types';
import { TDishDescriptionArr } from '../../API/dishAPI/TdishAPI';

const initialState : IdietsState = {
    smallDataAboutUserDiet : null,
    userHasDiet : false,
    smallDataAboutDiets : [],
    currentDietId : null,
    recomendedDishArr : [],
    dietsError : ""
}

const dietsSlice = createSlice({
    name : "diets",
    initialState,
    reducers: {
        setUserHasDiet : (state, action : PayloadAction<boolean>) => {
            state.userHasDiet = action.payload
        },
        setCurrentDietId : (state, action : PayloadAction<IsmallDataAboutDiets>) => {
            state.currentDietId = action.payload
        }
    },

    extraReducers: builder => {
        builder
            .addCase(getSmallDataAboutDiets.fulfilled.type, (state, action: PayloadAction<IsmallDataAboutDietsArr>) => {
                state.smallDataAboutDiets = action.payload
            })
            .addCase(getMealsByFilter.fulfilled.type, (state, action: PayloadAction<TDishDescriptionArr>) => {
                state.recomendedDishArr = action.payload
                state.dietsError = ""
            })
            .addCase(setUserDiet.fulfilled.type, (state, action: PayloadAction<IsmallDataAboutDiets>) => {
                state.userHasDiet = true;
            })
            .addCase(getMealsByFilter.rejected.type, (state, action: PayloadAction<string>) => {
                state.dietsError = action.payload
            })
            
    }
})

export const {setUserHasDiet, setCurrentDietId} = dietsSlice.actions

export default dietsSlice.reducer