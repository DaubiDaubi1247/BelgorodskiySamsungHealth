import ModalAllTraining from "../popUpForAllTraining/ModalAllTraining";
import styles from "../subscribeTraining/style.module.css"
import { useState } from 'react';
import { Button } from "react-bootstrap";
import { useAppDispatch, useAppSelector } from './../../../app/hooks';
import { getSmallDataAboutTrainings } from './../../../slices/training/thunk';
import TrainingItem from "../trainingItem/TrainingItem";


interface ISubscribeTrainingProps {
}

const SubscribeTraining: React.FunctionComponent<ISubscribeTrainingProps> = (props) => {

    let smallDataAboutTRainings = useAppSelector(state => state.training.smallDataTrainings)
    const trainingItemArr = smallDataAboutTRainings.map(el => <TrainingItem {...el}/>)

    const [show, setShow] = useState(false)
    const dispatch = useAppDispatch()

    const submitHandler = () => {

    }

    const handleShowAndClose = () => {
        setShow(!show);
        dispatch(getSmallDataAboutTrainings());
    }

    return (
        <div className={"d-flex " + styles.wrapper}>
            <span className="d-block">Кажется Вы не подписаны на тренировку...</span>
            <Button variant="primary" onClick={handleShowAndClose}>
                Просмотреть список тренировок
            </Button>
            <ModalAllTraining show={show} setShow={setShow} trainigCardArr={trainingItemArr}/>
        </div>
    )
};

export default SubscribeTraining;
