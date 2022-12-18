
import styles from "../../../common/form/form.module.css"

import { Form, Button } from 'react-bootstrap';
import FormError from './../../../common/form/formError/FormError';
import { useAppDispatch } from './../../../app/hooks';
import { createDiet } from "../../../slices/diets/thunk";
import { TCreateDush } from "../../../API/dietsAPI/TdietsAPI";
import { useForm } from 'react-hook-form';

interface AadminCreateDIetsProps {
}

const AdminCreateDIets: React.FunctionComponent<AadminCreateDIetsProps> = (props) => {

    const { register, handleSubmit, watch, formState: { errors } } = useForm();
    const watchAllFields = watch();

    const dishArr : Array<string> = []


    const dispatch = useAppDispatch()

    const createDietRequest = () => {
        let diet : TCreateDush = {
            label : watchAllFields.nameOfDiet,
            description : watchAllFields.description,
            dishes : dishArr
        }

        dispatch(createDiet(diet))
    }

    const addDishHandler = () => {
        dishArr.push(watchAllFields.dish)
    }

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <div>
                <Form onSubmit={handleSubmit(createDietRequest)} className={styles.form}>
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicEmail"
                    >
                        <Form.Label>Название Диеты</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Название диеты"
                            {...register("nameOfdiet", { required: true })}
                        />
                        {errors.nameOfTrain?.type === "required" && <FormError message='Поле является обязательным' />}
                    </Form.Group>

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
                    {errors.description && <FormError message='Поле является обязательным' />}
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <h4>Введите блюдо, затем нажмите кнопку "Добавить блюдо" или нажмите Cохранить тренировку"</h4>
                        <Form.Label>Блюдо</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Описание"
                            {...register("dish", { required: true })}
                        />
                    </Form.Group>
                    {errors.dish && <FormError message='Поле является обязательным' />}

                    <Button
                        variant="primary"
                        onClick={addDishHandler}
                    >
                        Сохранить Блюдо
                    </Button>
                    <Button
                        variant="primary"
                        type="submit"
                    >
                        Сохранить Диету
                    </Button>
                    </Form>
            </div>
        </div>
    );
};

export default AdminCreateDIets;
