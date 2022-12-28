import { useEffect } from 'react';
import { redirect, Route, Routes, useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import { AuthRoutes, MainRoutes } from '../../Routes/Routes';
import withAuthRedicrect from './../HOC/withAuthRedirect';


interface IMainProps {
}

const MainContainer: React.FunctionComponent<IMainProps> = (props) => {

    return (
        <h2>
            Добро пожаловать в наше приложение!!!
            (возможно когда то тут буду новости)
        </h2>
    );
};

export default withAuthRedicrect(MainContainer);
