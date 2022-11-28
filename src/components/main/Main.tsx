import * as React from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import { AuthRoutes } from '../../Routes/Routes';
import NavBar from '../navBar/NavBar';

interface IMainProps {
}

const Main: React.FunctionComponent<IMainProps> = (props) => {

    let isAuth = useAppSelector(state => state.auth.isAuth)

    let navigate = useNavigate();

    //if (!isAuth) navigate(AuthRoutes.authRoute)

  return (
    <div className='d-flex'>
        <NavBar/>
    </div>
  );
};

export default Main;
