
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import AuthForm from './Auth';
import { authUser, registrationUser } from './../../slices/auth/thunk';

interface IAuthContainerProps {
    isRegistration: boolean
}

const AuthContainer: React.FunctionComponent<IAuthContainerProps> = ({isRegistration}) => {
    let errorMessage = useAppSelector(state => isRegistration ? state.auth.registrationError : state.auth.loginError)
    let isAuth = useAppSelector(state => state.auth.isAuth)

    let handlerForSubmit = isRegistration ? registrationUser : authUser

    let navigate = useNavigate()
    if (isAuth) {
        navigate("/")
    }

  return <AuthForm 
        isRegistration={isRegistration} 
        handlerForSubmit={handlerForSubmit} 
        errorMessage={errorMessage}
    />
};

export default AuthContainer;
