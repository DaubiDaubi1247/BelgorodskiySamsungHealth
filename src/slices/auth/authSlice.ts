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
}

const initialState: AuthState = {
    isAuth: false,
    accessData: null,
    loginError: null,
    registrationError : null
};

// The function below is called a thunk and allows us to perform async logic. It
// can be dispatched like a regular action: `dispatch(incrementAsync(10))`. This
// will call the thunk with the `dispatch` function as the first argument. Async
// code can then be executed and other actions can be dispatched. Thunks are
// typically used to make async requests.
// export const incrementAsync = createAsyncThunk(
//   'counter/fetchCount',
//   async (amount: number) => {
//     const response = await fetchCount(amount);
//     // The value we return becomes the `fulfilled` action payload
//     return response.data;
//   }
// );

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    // The `reducers` field lets us define reducers and generate associated actions
    reducers: {
        setAuth: (state, action: PayloadAction<boolean>) => {
            state.isAuth = action.payload;
        },

        setAccessData: (state, action: PayloadAction<Inputs>) => {
            state.accessData = action.payload
        }

    },
    // The `extraReducers` field lets the slice handle actions defined elsewhere,
    // including actions generated by createAsyncThunk or in other slices.
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
