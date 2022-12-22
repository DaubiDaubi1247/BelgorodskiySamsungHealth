import * as React from 'react';
import { NavLink } from 'react-router-dom';

interface INavLinkConfigProps {
    path : string
    text : string
}

const NavLinkConfig: React.FunctionComponent<INavLinkConfigProps> = ({path, text}) => {
  return <NavLink className="nav-link" to={path}>{text}</NavLink>;
};

export default NavLinkConfig;
