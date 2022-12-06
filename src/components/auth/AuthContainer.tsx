
import { useEffect, useState } from 'react';
import { useNavigate, redirect } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import AuthForm from './Auth';
import { authUser, registrationUser } from './../../slices/auth/thunk';
import { main } from './../../Routes/Routes';

interface IAuthContainerProps {
    isRegistration: boolean
}

const AuthContainer: React.FunctionComponent<IAuthContainerProps> = ({ isRegistration }) => {
    let errorMessage = useAppSelector(state => isRegistration ? state.auth.registrationError : state.auth.loginError)
    let isAuth = useAppSelector(state => state.auth.isAuth)
    const navigate = useNavigate();

    let handlerForSubmit = isRegistration ? registrationUser : authUser

    if (isAuth) {
        navigate(main, {replace : true})
    }

    return <AuthForm
        isRegistration={isRegistration}
        handlerForSubmit={handlerForSubmit}
        errorMessage={errorMessage}
    />
};

export default AuthContainer;
