
import { useEffect } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import styles from "../../../common/form/form.module.css"
import FormError from './../../../common/form/formError/FormError';


interface IAdminAddTrainingMenuProps {
}

const AdminAddTrainingMenu: React.FunctionComponent<IAdminAddTrainingMenuProps> = (props) => {
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const onSubmit = () => console.log(1);
    const watchAllFields = watch();

    useEffect(() => {
        console.log(watchAllFields); 
    },[watchAllFields])


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
                        {...register("email", { required: true })}
                    />
                    {errors.email?.type === "required" && <FormError message='Поле является обязательным' />}
                </Form.Group>
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
                {errors.password && <FormError message='Поле является обязательным' />}
                <Button
                    variant="primary"
                    type="submit"
                >
                </Button>
            </Form>
        </div>
    );
};

export default AdminAddTrainingMenu;
