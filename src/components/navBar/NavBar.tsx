import * as React from 'react';
import NavLinkConfig from './../../common/navLinks/NavLinkConfig';
import { main, MainRoutes } from './../../Routes/Routes';

interface INavBarProps {
}

const NavBar: React.FunctionComponent<INavBarProps> = (props) => {
  return (
    <div className="h-100 align-self-start">
        <NavLinkConfig path={main} text='Главная'/>
        <NavLinkConfig path={MainRoutes.training} text='Мои тренировки'/>
    </div>
  );
};

export default NavBar;
