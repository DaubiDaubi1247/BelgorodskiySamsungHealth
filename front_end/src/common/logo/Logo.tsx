import logo from "./45735.jpg"
import styles from "./logo.module.css"
import { NavLink } from 'react-router-dom';
import { main } from './../../Routes/Routes';

const Logo: React.FunctionComponent = () => {
  return (
    <NavLink to={main}>
        <img className={styles.img} src={logo}/>
    </NavLink>
  )
};

export default Logo;
