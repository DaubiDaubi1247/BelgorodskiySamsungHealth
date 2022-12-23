
import { useAppSelector } from '../../../app/hooks';
import { IuserIS } from './../../../slices/user/Types';
import { useEffect } from 'react';
import { useAppDispatch } from './../../../app/hooks';
import { setUserMsg } from '../../../slices/user/userSlice';

interface IUserDataProfileProps {
    userData: IuserIS
}

const UserDataProfile: React.FunctionComponent<IUserDataProfileProps> = ({ userData }) => {

    let userMsg = useAppSelector(state => state.user.userMsg)

    return (
        <div>
            {userMsg.length !== 0 ? <p>{userMsg}</p> : <></>}
            <p>Ваш Профиль</p>
            <p>Имя : {userData.name}</p>
            <p>Вес : {userData.weight} кг</p>
            <p>Рост : {userData.height || "N/A"} см</p>
            <p>Количество выполненных тренировок : {userData.countOfCompletedTrainers}</p>
        </div>
    )
};

export default UserDataProfile;
