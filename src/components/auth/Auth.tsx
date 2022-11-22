import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useForm, SubmitHandler } from "react-hook-form";
import AuthError from './authError/AuthError';
import React from 'react';
import styles from "./from.module.css"
import { NavLink } from 'react-router-dom';
import { useAppDispatch } from '../../app/hooks';
import { Inputs } from '../../slices/auth/Types';


interface IAuthProps {
    isRegistration?: boolean
    handlerForSubmit : (data : Inputs) => any
}

const AuthForm: React.FC<IAuthProps> = (props) => {

    let dispatch = useAppDispatch();

    const { register, handleSubmit, watch, formState: { errors } } = useForm<Inputs>();

    const onSubmit: SubmitHandler<Inputs> = data => {
        dispatch(props.handlerForSubmit(data))
    }

    return (
        <div className={styles.formWrapper}>
            <Form onSubmit={handleSubmit(onSubmit)} className={styles.form}>
                <Form.Group
                    className="mb-3"
                    controlId="formBasicEmail"
                >
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter email"
                        {...register("email", { required: true, pattern: /\w{1,8}@\w{1,}/ })}
                    />
                    {errors.email?.type === "pattern" && <AuthError message='Не правильно написан email' />}
                    {errors.email?.type === "required" && <AuthError message='Поле является обязательным' />}
                </Form.Group>
                {props.isRegistration ? (
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicLogin"
                    >
                        <Form.Label>Логин</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="login"
                            {...register("login", { required: true })}
                        />
                        {errors.login && <AuthError message='Поле является обязательным' />}
                    </Form.Group>)
                 : ""}
                <Form.Group
                    className="mb-3"
                    controlId="formBasicPassword"
                >
                    <Form.Label>Пароль</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="Password"
                        {...register("password", { required: true })}
                    />
                </Form.Group>
                {errors.password && <AuthError message='Поле является обязательным' />}
                <Button
                    variant="primary"
                    type="submit"
                >
                    {props.isRegistration ? "Зарегестрироваться" : "Войти"}
                </Button>
                {!props.isRegistration ? 
                    (
                        <div>
                            <span>Нет аккаунта? </span>
                             <NavLink to="/auth/registration">Зарегестрироваться</NavLink>
                        </div>
                    )    
                    : ""
                }
            </Form>
        </div>
    );
}

export default AuthForm;