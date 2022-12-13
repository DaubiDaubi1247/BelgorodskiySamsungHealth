
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState } from 'react';
import { Card, ProgressBar } from 'react-bootstrap';
import { useAppSelector } from '../../../app/hooks';
import { Button } from 'react-bootstrap';
import DayList from './../daysList/DaysList';
import { useAppDispatch } from './../../../app/hooks';
import { deactivateTraining, getArrDaysExpires, setUserTrain } from './../../../slices/training/thunk';
import Progressbar from './progressBar/progressbar';
import DayListContainer from './../daysList/dayListContainer/DayListContainer';
import { setLoading } from '../../../slices/common/commonSlice';
import styles from "./TI.module.css"
import { deleteTraining } from '../../../slices/training/trainingSlice';

interface ITrainingProps extends ItrainigData {
    isUserTraining : boolean
    isAdmin? :boolean
}

const TrainingItem: React.FunctionComponent<ITrainingProps> = ({label, countOfDays, isUserTraining, id, isAdmin}) => {

    const [isVisible, setVisible] = useState(false);
    let percentAction = useAppSelector(state => state.training.percentOfProgress)

    
    let dispatch = useAppDispatch();

    const onClickHandler = () => {
        setVisible(!isVisible)

        if (!isVisible) dispatch(getArrDaysExpires(id))
    }

    let userId = useAppSelector(state => state.auth.accessData?.id)

    const subscribeTraininghandler = () => {
        if (userId) {
            dispatch(setUserTrain({userId : userId, trainId : id}))
        }
    }

    const deactivateTrainingHandler = () => {
        dispatch(deactivateTraining(id))
        dispatch(deleteTraining(id))
    }

    // delete thunk
    //const handlerForDeleteBtn = () => dispatch()

    return (
        <Card style={{ maxWidth: '400px', margin: "0 auto" }}>
          <Card.Body>
            <div className="d-flex justify-content-between">
                <Card.Title>{label}</Card.Title>
                {isAdmin ? <Button  className={styles.myButtons} onClick={deactivateTrainingHandler} variant="danger">x</Button> : <></>}
            </div>
            <Card.Subtitle className="mb-2 text-muted">Общее количество дней : {countOfDays}</Card.Subtitle>
            <Progressbar isUserTraining={isUserTraining} text='Процент выполнения' percentAction={percentAction}/>
            <div className="d-flex justify-content-between">
                <Button onClick={onClickHandler}>Посмотреть все упражения</Button>
                {isUserTraining ? <></> : <Button onClick={subscribeTraininghandler} className={styles.myButtons}>+</Button>}
            </div>
            <DayListContainer isVisible={isVisible}/>
          </Card.Body>
        </Card>
      );
};

export default TrainingItem;
