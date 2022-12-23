
import styles from "../../../common/form/form.module.css"

import { Form, Button } from 'react-bootstrap';
import FormError from './../../../common/form/formError/FormError';
import { useAppDispatch } from './../../../app/hooks';
import { createDiet } from "../../../slices/diets/thunk";
import { TCreateDush } from "../../../API/dietsAPI/TdietsAPI";
import { useForm } from 'react-hook-form';
import { useState } from "react";

interface AadminCreateDIetsProps {
}

const AdminCreateDIets: React.FunctionComponent<AadminCreateDIetsProps> = (props) => {

    const { register, handleSubmit, watch, formState: { errors }, reset } = useForm();
    const watchAllFields = watch();

    const [dishArr, setdishArr] = useState<Array<string>>([])

    const dispatch = useAppDispatch()

    const createDietRequest = () => {
        debugger
        let diet : TCreateDush = {
            label : watchAllFields.nameOfDiet,
            description : watchAllFields.description,
            dishes : dishArr
        }

        dispatch(createDiet(diet))
    }

    const addDishHandler = () => {
        dishArr.push(watchAllFields.dish)
        reset({
            dish: '',
            nameOfDiet : watchAllFields.nameOfDiet,
            description : watchAllFields.description
          })
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
                            {...register("nameOfDiet", { required: true })}
                        />
                        {errors.nameOfDiet?.type === "required" && <FormError message='Поле является обязательным' />}
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

                    <Button
                        variant="primary"
                        onClick={addDishHandler}
                    >
                        Сохранить Блюдо
                    </Button>
                    <Button
                        variant="primary"
                        className="d-flex"
                        style={{marginTop:"10px"}}
                        onClick={createDietRequest}
                    >
                        Сохранить Диету
                    </Button>
                    </Form>
            </div>
        </div>
    );
};

export default AdminCreateDIets;
