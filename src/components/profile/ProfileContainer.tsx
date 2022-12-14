
import { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap';
import { IuserDataForSet } from '../../API/userAPI/TuserAPI';
import { setLoading } from '../../slices/common/commonSlice';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { getUserData, setUserData } from './../../slices/user/thunk';
import withLoading from './../HOC/withAuthRedirect';
import UserDataProfile from './userData/UserDataProfile';
import ModalForSetUserData from './userData/modalForSetUserData/ModalForSetUserData';

interface IProfileContainerProps {
}

const ProfileContainer: React.FunctionComponent<IProfileContainerProps> = (props) => {
    
    const dispatch = useAppDispatch() 
    const id = useAppSelector(state => state.auth.accessData?.id)
    const userData = useAppSelector(state => state.user)

    const [show, setShow] = useState(false);

    const handleShowAndClose = () => setShow(!show);

    const setUserDataHandler = (userData : IuserDataForSet) => {
        dispatch(setUserData(userData))
    }

    useEffect(() => {
        dispatch(setLoading(true))
        if (id) dispatch(getUserData(id))
    })

  return (
    <div className="">
        <UserDataProfile userData={userData}/>
        <Button variant="primary" onClick={handleShowAndClose}>
          Обновить данные
        </Button>
        <ModalForSetUserData
            show={show}
            handleShowAndClose={handleShowAndClose}
            setUserData={setUserDataHandler}
            userId={id}
            userData={userData}
        />
    </div>
  )
};

export default withLoading(ProfileContainer);
