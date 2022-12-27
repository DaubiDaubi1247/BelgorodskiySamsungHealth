import { createSlice, current, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';
import { ItrainigData, IsmallDataAboutTrainings, TrainingDataArr, ArrDaysExpires } from './../../API/trainingAPI/TtrainingAPI';
import { CONST, ItrainitState } from './Types';
import { getSmallDataAboutTrainings, getUserTraining, getArrDaysExpires, setUserTrain, createTraining, updateDayUserTraining } from './thunk';
import { useAppSelector } from '../../app/hooks';


const initialState: ItrainitState = {
    smallDataTrainings : [],
    smallUserTraining : null,
    arrDaysExpires : [],
    percentOfProgress : CONST.NO_DATA,
    messageForCreate : "",
    userHasTraining : false,
    currentDay : CONST.NO_DATA,
    errorMsg : null
};

const trainingSlice = createSlice({
    name: 'training',
    initialState,

    reducers: {
        setSmallData: (state, action: PayloadAction<TrainingDataArr>) => {
            state.smallDataTrainings = action.payload;
        },

        deleteTraining: (state, action : PayloadAction<number>) => {
            state.smallDataTrainings = state.smallDataTrainings.filter(el => el.id !== action.payload)
        },
        setUserHasTraining: (state, action : PayloadAction<boolean>) => {
            state.userHasTraining = action.payload
        },

        setMessageForCreate :  (state, action : PayloadAction<string>) => {
            state.messageForCreate = action.payload
        },

        setErrorMsg : (state, action : PayloadAction<string | null>) => {
            state.errorMsg = action.payload
        }

    },

    extraReducers: (builder) => {                                                        //для санок!!!!
        builder
            .addCase(getSmallDataAboutTrainings.fulfilled.type, (state, action: PayloadAction<TrainingDataArr>) => {
                state.smallDataTrainings = action.payload
            })
            .addCase(getUserTraining.fulfilled.type, (state, action: PayloadAction<ItrainigData>) => {
                state.smallUserTraining = action.payload
                state.percentOfProgress = action.payload.presentOfProgress !== undefined ? action.payload.presentOfProgress : CONST.NO_DATA

                state.userHasTraining = action.payload !== undefined
                state.currentDay = action.payload.dayOfTraining + 1

            })

            .addCase(getArrDaysExpires.fulfilled.type, (state, action: PayloadAction<ArrDaysExpires>) => {
                state.arrDaysExpires = action.payload
            })

            .addCase(createTraining.fulfilled.type, (state, action: PayloadAction<string>) => {
                state.messageForCreate = action.payload
            })
            .addCase(setUserTrain.fulfilled.type, (state, action: PayloadAction<ArrDaysExpires>) => {
                state.userHasTraining = true

            })
            .addCase(updateDayUserTraining.fulfilled.type, (state, action: PayloadAction<ArrDaysExpires>) => {
                let days = state.smallUserTraining?.countOfDays ? state.smallUserTraining?.countOfDays : CONST.NO_DATA
                state.percentOfProgress = Math.round(state.currentDay / days * 100)
                state.currentDay++;
                
            })

            // -- rejecteds--
            
            .addCase(createTraining.rejected.type, (state, action: PayloadAction<string>) => {
                state.errorMsg = action.payload
            })

            .addCase(getArrDaysExpires.rejected, (state, action: any) => {
                state.errorMsg = action.payload
            })

            .addCase(getSmallDataAboutTrainings.rejected.type, (state, action: PayloadAction<string>) => {
                state.errorMsg = action.payload
            })

            .addCase(getUserTraining.rejected.type, (state, action: PayloadAction<string>) => {
                state.errorMsg = action.payload
            })

            .addCase(updateDayUserTraining.rejected.type, (state, action: PayloadAction<string>) => {
                debugger
                state.errorMsg = action.payload
                state.userHasTraining = false
            })
            
    },
});

export const { setSmallData,deleteTraining,setUserHasTraining,setMessageForCreate,setErrorMsg } = trainingSlice.actions;

export const selectSmallUserTraining = (state : RootState) => state.training.smallUserTraining


export default trainingSlice.reducer;
