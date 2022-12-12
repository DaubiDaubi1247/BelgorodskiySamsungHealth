
import "./Auth.css" 

interface IAuthErrorProps {
    message : string
}

const FormError: React.FunctionComponent<IAuthErrorProps> = (props) => {
  return (
    <p className="errorText">
        {props.message}
    </p>
  )
};

export default FormError;
