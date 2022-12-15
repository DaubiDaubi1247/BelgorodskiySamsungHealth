
import { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import styles from "../../../../../common/form/form.module.css"
import FormError from './../../../../../common/form/formError/FormError';
import { IuserDataForSet } from '../../../../../API/userAPI/TuserAPI';
import { IuserIS } from './../../../../../slices/user/Types';



interface IAdminAddTrainingMenuProps {
    setUserData : (userData : IuserDataForSet) => void
    userId? : number
    userData : IuserIS
    handleShowAndClose: () => void
}

const ModalForm: React.FunctionComponent<IAdminAddTrainingMenuProps> = ({setUserData, userId, userData,handleShowAndClose}) => {

    const { register, handleSubmit, watch, formState: { errors } } = useForm({
        defaultValues : {
            name : userData.name,
            weight : userData.weight,
            height : userData.height
        }
    });
    const watchAllFields = watch();

    const setUserDataWrapper = () => {
        const userDataRes : IuserDataForSet = {
            name : watchAllFields.name,
            weight : watchAllFields.weight,
            height : watchAllFields.height,
            id : userId ? userId : -1
        } 

        setUserData(userDataRes);
        handleShowAndClose()
    }

    return (
        <div className={styles.formWrapper + " d-flex justify-content-between"}>
            <div>
                <Form onSubmit={handleSubmit(setUserDataWrapper)} className={styles.form}>
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicEmail"
                    >
                        <Form.Label>Ваше имя</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Имя"
                            {...register("name", { required: true })}
                        />
                        {errors.name?.type === "required" && <FormError message='Поле является обязательным' />}
                    </Form.Group>
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Ваш вес</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Вес"
                            {...register("weight", { required: true, pattern: /\d/ })}
                        />
                    </Form.Group>
                    {errors.weight?.type === "pattern" && <FormError message='Поле может быть только числовым' />}
                    <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                    >
                        <Form.Label>Ваш рост</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Рост"
                            {...register("height", { required: true, pattern: /\d/  })}
                        />
                    </Form.Group>
                    {errors.height?.type === "pattern" && <FormError message='Поле может быть только числовым' />}
                    <Button
                        variant="primary"
                        type="submit"
                        // className={localStyles.btnForTraining}
                    >
                    Сохранить данные
                    </Button>
                </Form>
            </div>
        </div>
    );
};

export default ModalForm;
