
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { IuserIS } from './Types';
import { getUserData, setUserData} from './thunk';

const initialState: IuserIS = {
    name : "",
    weight : 0,
    height : 0,
    countOfCompletedTrainers : 0,
    userChangedata : false,
    userMsg : ""
};

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        setUserMsg: (state, action: PayloadAction<string>) => {
            state.userMsg = action.payload;
        },
        setUserChanedata : (state, action: PayloadAction<boolean>) => {
            state.userChangedata = action.payload;
        },

    },

    extraReducers: (builder) => {
        builder
            .addCase(getUserData.fulfilled.type , (state, action: PayloadAction<IuserIS>) => {
                state.name = action.payload.name
                state.weight = action.payload.weight
                state.height = action.payload.height
                state.countOfCompletedTrainers = action.payload.countOfCompletedTrainers
            })
            .addCase(setUserData.fulfilled.type , (state, action: PayloadAction<string>) => {
                debugger
                state.userChangedata = true
                state.userMsg = action.payload
            })
    },
    
});

export const { setUserMsg,setUserChanedata } = userSlice.actions;

export default userSlice.reducer;
