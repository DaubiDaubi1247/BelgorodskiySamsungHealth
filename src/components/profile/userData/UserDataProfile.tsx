import * as React from 'react';
import { IuserIS } from './../../../slices/user/Types';

interface IUserDataProfileProps {
    userData : IuserIS
}

const UserDataProfile: React.FunctionComponent<IUserDataProfileProps> = ({userData}) => {
  return (
    <div>
        <p>Имя : {userData.name}</p>
        <p>Вес : {userData.weight} кг</p>
        <p>Рост : {userData.height || "N/A"} см</p>
        <p>Количество выполненных тренировок : {userData.countOfCompletedTrainers}</p>
    </div>
  )
};

export default UserDataProfile;
