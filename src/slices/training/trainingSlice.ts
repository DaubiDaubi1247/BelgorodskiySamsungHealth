import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';
import { ItrainigData, IsmallDataAboutTrainings, TrainingDataArr, ArrDaysExpires } from './../../API/trainingAPI/TtrainingAPI';
import { CONST, ItrainitState } from './Types';
import { getSmallDataAboutTrainings, getUserTraining, getArrDaysExpires, setUserTrain, createTraining } from './thunk';
import { useAppSelector } from '../../app/hooks';


const initialState: ItrainitState = {
    smallDataTrainings : [],
    smallUserTraining : null,
    arrDaysExpires : [],
    percentOfProgress : CONST.NO_DATA,
    messageForCreate : ""

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

                // state.smallUserTraining = {id : 1, name : "Набор массы", countDays : 9}
                // state.today = Math.round(2 / 9 * 100)
            })
            .addCase(getArrDaysExpires.fulfilled.type, (state, action: PayloadAction<ArrDaysExpires>) => {
                
                state.arrDaysExpires = action.payload
            })
            .addCase(createTraining.fulfilled.type, (state, action: PayloadAction<string>) => {
                
                state.messageForCreate = action.payload
            })
            // .addCase(setUserTrain.fulfilled.type, (state, action: PayloadAction<ArrDaysExpires>) => {
            //     state.arrDaysExpires = action.payload
            // })
            
    },
});

export const { setSmallData,deleteTraining } = trainingSlice.actions;


export default trainingSlice.reducer;
