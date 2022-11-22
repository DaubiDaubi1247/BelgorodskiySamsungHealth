import * as React from 'react';
import { useAppSelector } from '../app/hooks';
import { useNavigate} from "react-router-dom"

interface IAuthProps {
    Component: React.FC
}

const withAuthRedirect : React.FC<IAuthProps> = ({Component}) => {
    let isAuth: boolean = useAppSelector(state => state.auth.isAuth)

    let navigate = useNavigate();


    if (isAuth) {
        navigate("/")
    }
    return <Component/>
};

export default withAuthRedirect
