
import Logo from '../../common/logo/Logo';
import styles from "./header.module.css"

interface IHeaderProps {
}

const Header: React.FunctionComponent<IHeaderProps> = (props) => {
  return (
    <header className={styles.header}>
        <div className={styles.flexContainer + " mh-100 mw-100 d-flex p-2"}>
            <Logo/>
        </div>
    </header>
  );
};

export default Header;
