
interface IMessagefromServerProps {
    message : string
}

const MessagefromServer: React.FunctionComponent<IMessagefromServerProps> = ({message}) => {
  return <>
    <span>{message}</span>
  </>
};

export default MessagefromServer;
