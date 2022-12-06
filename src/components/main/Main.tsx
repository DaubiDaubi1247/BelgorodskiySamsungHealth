import { useEffect } from 'react';
import { redirect, Route, Routes, useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import { AuthRoutes, MainRoutes } from '../../Routes/Routes';


interface IMainProps {
}

const MainContainer: React.FunctionComponent<IMainProps> = (props) => {
    let isAuth = useAppSelector(state => state.auth.isAuth)
    const navigate = useNavigate()

    useEffect(() => {
        if (!isAuth) navigate(AuthRoutes.authRoute, {replace : true})
    })

    return (
        <></>
    );
};

export default MainContainer;
