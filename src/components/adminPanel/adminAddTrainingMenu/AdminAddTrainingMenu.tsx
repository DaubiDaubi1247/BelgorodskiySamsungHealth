
import { useEffect, useState } from 'react';
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
    const [isVisble, setisVisble] = useState(false)

    useEffect(() => {
        console.log(watchAllFields); 
    },[watchAllFields])

    const newTrainingDescription = () => {
        let res : Array<any> = [];
        res.push(<p>Название : {watchAllFields.nameOfTrain}</p>)
        res.push(<p>Количество дней : {watchAllFields.countOfDays}</p>)
        res.push(<p>описание  : {watchAllFields.description}</p>)

        return res
    } 

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <Form onSubmit={handleSubmit(onSubmit)} className={styles.form}>
                <Form.Group
                    className="mb-3"
                    controlId="formBasicEmail"
                >
                    <Form.Label>Название Тренировки</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Название тренировки"
                        {...register("nameOfTrain", { required: true })}
                    />
                    {errors.nameOfTrain?.type === "required" && <FormError message='Поле является обязательным' />}
                </Form.Group>
                <Form.Group
                    className="mb-3"
                    controlId="formBasicPassword"
                >
                    <Form.Label>Количество дней</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Количество дней"
                        {...register("countOfDays", { required: true })}
                    />
                </Form.Group>
                {errors.password && <FormError message='Поле является обязательным' />}

                <Form.Group
                    className="mb-3"
                    controlId="formBasicPassword"
                >
                    <Form.Label>Описание</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Описание"
                        {...register("description", { required: true })}
                    />
                </Form.Group>
                {errors.password && <FormError message='Поле является обязательным' />}
                <Button
                    variant="primary"
                    onClick={() => setisVisble(true)}
                >
                Сохранить общее описание
                </Button>
            </Form>
            <div>{isVisble ? newTrainingDescription() : <></>}</div>
        </div>
    );
};

export default AdminAddTrainingMenu;
