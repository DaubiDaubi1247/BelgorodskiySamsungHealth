import * as React from 'react';
import "./Auth.css" 

interface IAuthErrorProps {
    message : string
}

const AuthError: React.FunctionComponent<IAuthErrorProps> = (props) => {
  return (
    <p className="errorText">
        {props.message}
    </p>
  )
};

export default AuthError;
