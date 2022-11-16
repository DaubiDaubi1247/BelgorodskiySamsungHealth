import * as React from 'react';
import { useAppSelector } from '../app/hooks';
import AuthForm from './../components/auth/Auth';
import {Redirect, useNavigate} from "react-router-dom"

interface IAuthProps {
    component: React.FC

}

const withAuthRedirect: React.FunctionComponent<IAuthProps> = (props) => {
    let isAuth: Boolean = useAppSelector(state => state.auth.isAuth)

    let navigate = useNavigate();

    if (!isAuth) {
        return navigate("/")
    }
    return (
        props.component
    )
};

export default withAuthRedirect;
