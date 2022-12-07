
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState } from 'react';
import { Card, ProgressBar } from 'react-bootstrap';
import { useAppSelector } from '../../../app/hooks';
import { Button } from 'react-bootstrap';
import DayList from './../daysList/DaysList';
import { useAppDispatch } from './../../../app/hooks';
import { getArrDaysExpires } from './../../../slices/training/thunk';
import Progressbar from './progressBar/progressbar';
import DayListContainer from './../daysList/dayListContainer/DayListContainer';
import { setLoading } from '../../../slices/common/commonSlice';

interface ITrainingProps extends ItrainigData {
    isUserTraining : boolean
}

const TrainingItem: React.FunctionComponent<ITrainingProps> = ({label, countOfDays, isUserTraining, id}) => {

    const [isVisible, setVisible] = useState(false);
    let percentAction = useAppSelector(state => state.training.today)
    
    let dispatch = useAppDispatch();

    const onClickHandler = () => {
        setVisible(!isVisible)

        if (!isVisible) dispatch(getArrDaysExpires(id))
    }
    const subscribeTraininghandler = () => 1
    return (
        <Card style={{ width: '100%', margin: "0 auto" }}>
          <Card.Body>
            <Card.Title>{label}</Card.Title>
            <Card.Subtitle className="mb-2 text-muted">Общее количество дней : {countOfDays}</Card.Subtitle>
            <Progressbar isUserTraining={isUserTraining} text='Процент выполнения' percentAction={percentAction}/>
            <div className="d-flex justify-content-between">
                <Button onClick={onClickHandler}>Посмотреть все упражения</Button>
                {isUserTraining ? <></> : <Button onClick={subscribeTraininghandler}>+</Button>}
            </div>
            <DayListContainer  isVisible={isVisible}/>
          </Card.Body>
        </Card>
      );
};

export default TrainingItem;
