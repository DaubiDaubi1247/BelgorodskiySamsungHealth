import ModalAllTraining from "../popUpForAllTraining/ModalAllTraining";
import styles from "../subscribeTraining/style.module.css"
import { useState, useEffect } from 'react';
import { Button } from "react-bootstrap";
import { useAppDispatch, useAppSelector } from './../../../app/hooks';
import { getSmallDataAboutTrainings } from './../../../slices/training/thunk';
import TrainingItem from "../trainingItem/TrainingItem";
import MessagefromServer from "../../../common/messageFromServer/MessageFromServer";
import { setErrorMsg } from "../../../slices/training/trainingSlice";


interface ISubscribeTrainingProps {
    isUserTrain: boolean
}

const SubscribeTraining: React.FunctionComponent<ISubscribeTrainingProps> = ({ isUserTrain }) => {

    let smallDataAboutTRainings = useAppSelector(state => state.training.smallDataTrainings)
    const errorMsg = useAppSelector(state => state.training.errorMsg);

    const trainingItemArr = smallDataAboutTRainings.map(el => <TrainingItem {...el} isUserTraining={false} />)

    const [show, setShow] = useState(false)

    const dispatch = useAppDispatch()

    const handleShowAndClose = () => {
        setShow(!show);
        dispatch(getSmallDataAboutTrainings());
    }

    useEffect(() => function() {
        dispatch(setErrorMsg(null))
    })

    return (
        <div className="">
            {errorMsg ? <MessagefromServer message={errorMsg} isError={true} /> :
                <div className={"d-flex " + styles.wrapper}>
                    {isUserTrain ? <span className="d-block">Кажется Вы не подписаны на тренировку...</span> : <></>}
                    <Button variant="primary" onClick={handleShowAndClose}>
                        Просмотреть список тренировок
                    </Button>
                    <ModalAllTraining show={show} setShow={setShow} trainigCardArr={trainingItemArr} />
                </div>
            }
        </div>
    )
};

export default SubscribeTraining;
