import * as React from 'react';
import { useAppSelector } from '../../app/hooks';
import NavLinkConfig from './../../common/navLinks/NavLinkConfig';
import { main, MainRoutes, admin, profile, diets } from './../../Routes/Routes';

interface INavBarProps {
}

const NavBar: React.FunctionComponent<INavBarProps> = (props) => {

    let isAdmin = useAppSelector(state => state.auth.isAdmin)

  return (
    <div className="h-100 align-self-start">
        <NavLinkConfig path={main} text='Главная'/>
        <NavLinkConfig path={MainRoutes.training} text='Мои тренировки'/>
        <NavLinkConfig path={profile} text='Мой Профиль'/>
        <NavLinkConfig path={diets} text='Моя диета'/>
        {isAdmin ? <NavLinkConfig path={admin} text='Панель администратора'/> : <></> }
    </div>
  );
};

export default NavBar;
