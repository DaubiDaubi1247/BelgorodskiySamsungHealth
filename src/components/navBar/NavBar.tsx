import * as React from 'react';
import NavLinkConfig from './../../common/navLinks/NavLinkConfig';

interface INavBarProps {
}

const NavBar: React.FunctionComponent<INavBarProps> = (props) => {
  return (
    <div className="">
        <NavLinkConfig path="/" text='Главная'/>
        <NavLinkConfig path="/training" text='Мои тренировки'/>
    </div>
  );
};

export default NavBar;
