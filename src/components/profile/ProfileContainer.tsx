
import { useEffect } from 'react';
import { setLoading } from '../../slices/common/commonSlice';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { getUserData } from './../../slices/user/thunk';
import withLoading from './../HOC/withAuthRedirect';
import UserDataProfile from './userData/UserDataProfile';

interface IProfileContainerProps {
}

const ProfileContainer: React.FunctionComponent<IProfileContainerProps> = (props) => {
    
    const dispatch = useAppDispatch() 
    const id = useAppSelector(state => state.auth.accessData?.id)
    const userData = useAppSelector(state => state.user)

    useEffect(() => {
        dispatch(setLoading(true))
        if (id) dispatch(getUserData(id))
    })

  return (
    <div className="">
        <UserDataProfile userData={userData}/>
    </div>
  )
};

export default withLoading(ProfileContainer);
