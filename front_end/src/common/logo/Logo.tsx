import logo from "./45735.jpg"
import styles from "./logo.module.css"

const Logo: React.FunctionComponent = () => {
  return (
    <div>
        <img className={styles.img} src={logo}/>
    </div>
  )
};

export default Logo;
