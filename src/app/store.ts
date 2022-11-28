import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import authSlice from '../slices/auth/authSlice';
import trainingSlice from './../slices/training/trainingSlice';

export const store = configureStore({
  reducer: {
    auth : authSlice,
    training : trainingSlice
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
