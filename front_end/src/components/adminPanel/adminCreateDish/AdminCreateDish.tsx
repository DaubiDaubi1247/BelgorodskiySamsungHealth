
import styles from "../../../common/form/form.module.css"

import { Form, Button } from 'react-bootstrap';
import FormError from './../../../common/form/formError/FormError';
import { useAppDispatch, useAppSelector } from './../../../app/hooks';
import { createDiet } from "../../../slices/diets/thunk";
import { TCreateDush } from "../../../API/dietsAPI/TdietsAPI";
import { useForm } from 'react-hook-form';
import { useState } from "react";
import { createDish } from './../../../slices/dish/thunk';
import { IDishCreate } from './../../../API/dishAPI/TdishAPI';

interface AadminCreateDIetsProps {
}

const AdminCreateDish: React.FunctionComponent<AadminCreateDIetsProps> = (props) => {

    let mealCreateRes = useAppSelector(state => state.dish.mealCreateRes)

    const { register, handleSubmit, watch, formState: { errors }, reset } = useForm();
    const watchAllFields = watch();

    const dispatch = useAppDispatch()

    const createDietRequest = () => {
        let diet : IDishCreate = {
            label : watchAllFields.nameOfDish,
            type : watchAllFields.type,
            calPerGram : watchAllFields.сal,
            proteinsPerGram : watchAllFields.prot,
            fatsPerGram : watchAllFields.fat,
            carbsPerGram : watchAllFields.carbs,
            mealTimes : [watchAllFields.time]
        }

        reset()

        dispatch(createDish(diet))
    }

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <div>
                {mealCreateRes ? <span style={{color : "blue"}}>{mealCreateRes}</span> : <></>}
                <Form onSubmit={handleSubmit(createDietRequest)} className={styles.form}>
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicEmail"
                    >
                        <Form.Label>Название Блюда</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Название блюда"
                            {...register("nameOfDish", { required: true })}
                        />
                        {errors.nameOfDish?.type === "required" && <FormError message='Поле является обязательным' />}
                    </Form.Group>

                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Тип блюда</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Тип"
                            {...register("type", { required: true })}
                        />
                    </Form.Group>
                    {errors.description && <FormError message='Поле является обязательным' />}
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Калории</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Калории"
                            {...register("сal", { required: true, pattern: /\d/ })}
                        />
                    </Form.Group>
                    {errors.cal?.type === "pattern" && <FormError message='Поле должно быть числовым' />}

                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Протеин</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Протеин"
                            {...register("prot", { required: true, pattern: /\d/ })}
                        />
                    </Form.Group>
                    {errors.cal?.type === "pattern" && <FormError message='Поле должно быть числовым' />}

                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Жиры</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="жиры"
                            {...register("fat", { required: true, pattern: /\d/ })}
                        />
                    </Form.Group>
                    {errors.fat?.type === "pattern" && <FormError message='Поле должно быть числовым' />}

                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Карбс</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="карбс"
                            {...register("carbs", { required: true, pattern: /\d/ })}
                        />
                    </Form.Group>
                    {errors.carbs?.type === "pattern" && <FormError message='Поле должно быть числовым' />}

                    <Form.Group
                        className="mb-3"
                        controlId="formBasicEmail"
                    >
                        <Form.Label>Время приема пищи</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Прием"
                            {...register("time", { required: true })}
                        />
                        {errors.nameOfDish?.type === "required" && <FormError message='Поле является обязательным' />}
                    </Form.Group>

                    <Button
                        variant="primary"
                        onClick={createDietRequest}
                    >
                        Сохранить Блюдо
                    </Button>
                    </Form>
            </div>
        </div>
    );
};

export default AdminCreateDish;
