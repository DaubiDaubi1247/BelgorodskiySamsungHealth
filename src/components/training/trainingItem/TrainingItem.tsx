
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState, useEffect } from 'react';
import { Card, ProgressBar } from 'react-bootstrap';
import { useAppSelector } from '../../../app/hooks';
import { Button } from 'react-bootstrap';
import { useAppDispatch } from './../../../app/hooks';
import { deactivateTraining, getArrDaysExpires, setUserTrain, updateDayUserTraining } from './../../../slices/training/thunk';
import Progressbar from './progressBar/progressbar';
import DayListContainer from './../daysList/dayListContainer/DayListContainer';
import { setLoading } from '../../../slices/common/commonSlice';
import styles from "./TI.module.css"
import { deleteTraining, setErrorMsg } from '../../../slices/training/trainingSlice';
import MessagefromServer from './../../../common/messageFromServer/MessageFromServer';
import { ClickAwayListener } from '@material-ui/core';


interface ITrainingProps extends ItrainigData {
    isUserTraining: boolean
    isAdmin?: boolean
}

const TrainingItem: React.FunctionComponent<ITrainingProps> = ({ label, countOfDays, isUserTraining, id, isAdmin }) => {

    const [isVisible, setVisible] = useState(false);
    let percentAction = useAppSelector(state => state.training.percentOfProgress)
    let userId = useAppSelector(state => state.auth.accessData?.id)
    let errorMsg = useAppSelector(state => state.training.errorMsg)


    let dispatch = useAppDispatch();

    const onClickHandler = () => {
        setVisible(!isVisible)

        dispatch(getArrDaysExpires(id))
    }


    const subscribeTraininghandler = () => {
        if (userId) {
            dispatch(setUserTrain({ userId: userId, trainId: id }))
        }
    }

    const deactivateTrainingHandler = () => {
        dispatch(deactivateTraining(id))
        dispatch(deleteTraining(id))
    }

    const endOfDayTraining = () => {
        if (userId) dispatch(updateDayUserTraining(userId))
    }
    
    return (
        <ClickAwayListener onClickAway={() => {setVisible(false)}}>
                <Card style={{ maxWidth: '400px', margin: "0 auto" }}>
                    <Card.Body>
                        <div className="d-flex justify-content-between">
                            <Card.Title>{label}</Card.Title>
                            {isAdmin ? <Button className={styles.myButtons} onClick={deactivateTrainingHandler} variant="danger">x</Button> : <></>}
                        </div>

                        <Card.Subtitle className="mb-2 text-muted">Общее количество дней : {countOfDays}</Card.Subtitle>
                        <Progressbar isUserTraining={isUserTraining} text='Процент выполнения' percentAction={percentAction} />

                        <div className="d-flex justify-content-between">
                            <Button onClick={onClickHandler} >Посмотреть все упражения</Button>
                            {isUserTraining ? <></> : <Button onClick={subscribeTraininghandler} className={styles.myButtons}>+</Button>}
                        </div>

                        <DayListContainer isVisible={isVisible} isUserTraining={isUserTraining} setVisible={setVisible}/>
                        {isUserTraining ? <Button style={{ marginTop: "10px" }} onClick={endOfDayTraining} >Закончить день</Button> : <></>}
                    </Card.Body>
                </Card>
            
        </ClickAwayListener>
    );
};

export default TrainingItem;
