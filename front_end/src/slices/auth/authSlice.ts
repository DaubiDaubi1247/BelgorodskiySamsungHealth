import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';
import { Inputs, ResponseStatus } from './Types';
import { authUser, registrationUser } from './thunk';
import { IResponse } from '../../API/authAPI/API_T';

export interface AuthState {
    isAuth: boolean
    accessData: Inputs | null
    loginError: string | null
    registrationError: string | null
    isAdmin : boolean | null
}

const initialState: AuthState = {
    isAuth: false,
    accessData: null,
    loginError: null,
    registrationError : null,
    isAdmin : false
};


export const authSlice = createSlice({
    name: 'auth',
    initialState,
    // The `reducers` field lets us define reducers and generate associated actions
    reducers: {
        setAuth: (state, action: PayloadAction<boolean>) => {
            state.isAuth = action.payload;
        },

        setAccessData: (state, action: PayloadAction<Inputs | null>) => {
            state.accessData = action.payload
        }

    },

    extraReducers: (builder) => {                                                        //для санок!!!!
        builder
            .addCase(registrationUser.fulfilled.type, (state, action: PayloadAction<IResponse>) => {
                if (action.payload.status === ResponseStatus.SUCCESS) {
                    state.accessData = action.payload.usersOfApp
                    state.isAuth = true
                } else {
                    state.registrationError = action.payload.message                    
                }
            })

            .addCase(authUser.fulfilled.type, (state, action: PayloadAction<IResponse>) => {
                if (action.payload.status === ResponseStatus.SUCCESS) {
                    state.accessData = action.payload.usersOfApp
                    state.isAdmin = action.payload.usersOfApp.isAdmin
                    state.isAuth = true
                } else {
                    state.loginError = action.payload.message
                }
            })

    },
});

export const { setAuth, setAccessData } = authSlice.actions;

// The function below is called a selector and allows us to select a value from
// the state. Selectors can also be defined inline where they're used instead of
// in the slice file. For example: `useSelector((state: RootState) => state.counter.value)`
export const selectAuth = (state: RootState) => state.auth.isAuth;
export const selectAccessData = (state: RootState) => state.auth.accessData;

    
export default authSlice.reducer;
