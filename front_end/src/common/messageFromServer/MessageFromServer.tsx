import styles from "./message.module.css"

interface IMessagefromServerProps {
    message: string
    isError: boolean
}

const MessagefromServer: React.FunctionComponent<IMessagefromServerProps> = ({ message, isError }) => {
    return <>
        <span className={!isError ? styles.succes : styles.rejected}>{message}</span>
    </>
};

export default MessagefromServer;
