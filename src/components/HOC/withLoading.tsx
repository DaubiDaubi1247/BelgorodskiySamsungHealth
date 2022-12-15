import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAppSelector } from '../../app/hooks';
import Preloader from '../../common/preloader/Preloader';
import { AuthRoutes } from '../../Routes/Routes';

const withLoading = (Component : any) => (props : any) => {
    let isLoading = useAppSelector(state => state.common.isLoading) 

    if (isLoading) return <Preloader/>

    return <Component {...props} />;
};

export default withLoading