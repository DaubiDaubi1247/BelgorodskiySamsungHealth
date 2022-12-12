
import { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import styles from "../../../common/form/form.module.css"
import localStyles from "./addTraining.module.css"
import FormError from './../../../common/form/formError/FormError';
import { IdayDescription } from './../../../API/trainingAPI/TtrainingAPI';
import AdminAddTrainingInnerForm from './innerForm/InnerForm';
import { is } from 'immer/dist/internal';
import { useAppDispatch } from './../../../app/hooks';
import { createTraining } from './../../../slices/training/thunk';


interface IAdminAddTrainingMenuProps {
}

const AdminAddTrainingMenu: React.FunctionComponent<IAdminAddTrainingMenuProps> = (props) => {
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const onSubmit = () => console.log(1);
    const watchAllFields = watch();

    const [isVisble, setisVisble] = useState(false)
    const daysOfTraining : Array<IdayDescription> = []

    const newTrainingDescription = () => {
        let res : Array<any> = [];
        res.push(<p className={localStyles.resStr}>Название : {watchAllFields.nameOfTrain}</p>)
        res.push(<p className={localStyles.resStr}>Количество дней : {watchAllFields.countOfDays}</p>)
        res.push(<p className={localStyles.resStr}>Описание  : {watchAllFields.description}</p>)

        return res
    } 

    const dispatch = useAppDispatch()

    const createTrainingRequest = () => dispatch(createTraining(daysOfTraining));

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <div>
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
                        className={localStyles.btnForTraining}
                    >
                    Сохранить общее описание
                    </Button>
                
                </Form>
                <AdminAddTrainingInnerForm
                        isVisible={isVisble}
                        daysOfTraining={daysOfTraining}
                    />
            </div>
            <div className={localStyles.resBlock}>
                {isVisble ? newTrainingDescription() : <></>}
            </div>
        </div>
    );
};

export default AdminAddTrainingMenu;
