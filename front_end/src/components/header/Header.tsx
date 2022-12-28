
import Logo from '../../common/logo/Logo';
import styles from "./header.module.css"
import Profile from './profile/Profile';
import { NavLink } from 'react-router-dom';
import { AuthRoutes } from '../../Routes/Routes';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { setAccessData, setAuth } from '../../slices/auth/authSlice';

interface IHeaderProps {
}

const Header: React.FunctionComponent<IHeaderProps> = (props) => {

    let isAuth = useAppSelector(state => state.auth.isAuth)

    const exitFromAccount = () => {
        window.location.reload()
    }

  return (
    <header className={styles.header}>
        <div className={styles.flexContainer + " mh-100 mw-100 d-flex p-2"}>
            <Logo/>
            <h3 className="title">
                BelgorodskiySamsungHealth
            </h3>
            <div className="">
                <Profile/>
                {isAuth ? <NavLink to={AuthRoutes.authRoute} style={{marginLeft : "10px", color: "white", textDecoration : 'none', fontSize : '20px'}} onClick={exitFromAccount}>Выход</NavLink> : ""}
            </div>
        </div>
    </header>
  );
};

export default Header;
