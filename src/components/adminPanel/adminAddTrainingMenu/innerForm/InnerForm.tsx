
import { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import styles from "../../../../common/form/form.module.css"
import localStyles from "../addTraining.module.css"
import FormError from './../../../../common/form/formError/FormError';
import { DayExercises, Exercises, IdayDescription } from './../../../../API/trainingAPI/TtrainingAPI';
import DayDescription from './../../../training/daysList/dayDescription/DayDescription';



interface IAdminAddTrainingMenuProps {
    daysOfTraining : Array<IdayDescription>
    isVisible : boolean
    createTraining : () => void
}

const AdminAddTrainingInnerForm: React.FunctionComponent<IAdminAddTrainingMenuProps> = ({daysOfTraining,isVisible,createTraining}) => {


    const { register, handleSubmit, watch, formState: { errors },reset } = useForm();
    const onSubmit = () => console.log(1);
    const watchAllFields = watch();

    const [counterDays, setcounterDays] = useState(1)
    const [sets, setsets] = useState<DayExercises>([])


    if (!isVisible) return <></>

    const arrForPrint = () => daysOfTraining.map(el => <DayDescription dayData={el}/>)

    const setExercise = () => {
        let exercise : Exercises = {
            restTimeInSec : watchAllFields.restTimeInSec,
            numberOfRepetitions : watchAllFields.numberOfRepetitions,
            exercise : {
                label : watchAllFields.label,
                description : watchAllFields.description
            }
        }
        sets.push(exercise)
        reset()
    }

    const setDayOfTraining = () => {
        let day :IdayDescription = {
            numberOfDay : counterDays,
            sets : [...sets]
        }
        daysOfTraining.push(day)
        setcounterDays(counterDays + 1);
        sets.splice(0)
    }

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <Form onSubmit={handleSubmit(onSubmit)} className={styles.form}>
            <Form.Group
                    className="mb-3"
                    controlId="formBasicEmail"
                >
                    <Form.Label>Упражение</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Упражнение"
                        {...register("label", { required: true })}
                    />
                    {errors.label?.type === "required" && <FormError message='Поле является обязательным' />}
                </Form.Group>
                <Form.Group
                    className="mb-3"
                    controlId="formBasicEmail"
                >
                    <Form.Label>Описание Упражения</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Описание"
                        {...register("description", { required: true })}
                    />
                    {errors.description?.type === "required" && <FormError message='Поле является обязательным' />}
                </Form.Group>
                <Form.Group
                    className="mb-3"
                    controlId="formBasicEmail"
                >
                    <Form.Label>Количество повторений</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Повторения"
                        {...register("numberOfRepetitions", { required: true })}
                    />
                    {errors.numberOfRepetitions?.type === "required" && <FormError message='Поле является обязательным' />}
                </Form.Group>
                <Form.Group
                    className="mb-3"
                    controlId="formBasicEmail"
                >
                    <Form.Label>Количество отдыха(сек)</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Отдых"
                        {...register("restTimeInSec", { required: true })}
                    />
                    {errors.nameOfTrain?.type === "required" && <FormError message='Поле является обязательным' />}
                </Form.Group>

                <Button
                    variant="primary"
                    onClick={setExercise}
                    className={localStyles.btnForTraining}
                >
                Зафиксировать Упражение
                </Button>
                <Button
                    variant="primary"
                    onClick={setDayOfTraining}
                    className={localStyles.btnForTraining}
                >
                Закончить день тренировки
                </Button>
                <Button
                    variant="primary"
                    onClick={createTraining}
                    className={localStyles.btnForTraining}
                >
                Закончить создание тренировки
                </Button>
            </Form>
            <div className={localStyles.resBlock}>
                {arrForPrint()}
            </div>
        </div>
    );
};

export default AdminAddTrainingInnerForm;
