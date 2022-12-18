import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import { AuthRoutes } from '../../Routes/Routes';

const withAuthRedicrect = (Component : any) => (props : any) => {
    const navigate = useNavigate()
    let isAuth = useAppSelector(state => state.auth.isAuth);


    useEffect(() => {
        if (!isAuth) navigate(AuthRoutes.authRoute)
    })


    return <Component {...props} />;
};

export default withAuthRedicrect