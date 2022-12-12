
import { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import styles from "../../../common/form/form.module.css"
import localStyles from "./addTraining.module.css"
import FormError from './../../../common/form/formError/FormError';
import { DayExercises, IdayDescription, TCreateTrainig } from './../../../API/trainingAPI/TtrainingAPI';
import AdminAddTrainingInnerForm from './innerForm/InnerForm';
import { is } from 'immer/dist/internal';
import { useAppDispatch } from './../../../app/hooks';
import { createTraining } from './../../../slices/training/thunk';


interface IAdminAddTrainingMenuProps {
}

const AdminAddTrainingMenu: React.FunctionComponent<IAdminAddTrainingMenuProps> = (props) => {
    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const watchAllFields = watch();

    const [isVisble, setisVisble] = useState(false)
    const [numberOfDay, setnumberOfDay] = useState(1)
    const [sets, setsets] = useState<DayExercises>([])

    const daysOfTraining : Array<IdayDescription> = []

    const dispatch = useAppDispatch()


    const createTrainingRequest = () => {
        let training : TCreateTrainig = {
            label : watchAllFields.nameOfTrain,
            countOfDays : watchAllFields.countOfDays,
            description : watchAllFields.description,
            daysOfTrainings : daysOfTraining
        }

        dispatch(createTraining(training))
    }

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <div>
                <Form onSubmit={handleSubmit(createTrainingRequest)} className={styles.form}>
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
                
                <AdminAddTrainingInnerForm
                        isVisible={isVisble}
                        daysOfTraining={daysOfTraining}
                        createTraining={createTrainingRequest}
                />
                    
                </Form>
            </div>
        </div>
    );
};

export default AdminAddTrainingMenu;
