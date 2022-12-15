import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import authSlice from '../slices/auth/authSlice';
import commonSlice from '../slices/common/commonSlice';
import userSlice from '../slices/user/userSlice';
import trainingSlice from './../slices/training/trainingSlice';
import dietsSlice from './../slices/diets/diets';

export const store = configureStore({
  reducer: {
    auth : authSlice,
    training : trainingSlice,
    common : commonSlice,
    user : userSlice,
    diets : dietsSlice
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;

//@ts-ignore
window.store = store.getState();
