
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState } from 'react';
import { Card, ProgressBar } from 'react-bootstrap';
import { useAppSelector } from '../../../app/hooks';
import { Button } from 'react-bootstrap';
import DayList from './../daysList/DaysList';
import { useAppDispatch } from './../../../app/hooks';
import { getArrDaysExpires } from './../../../slices/training/thunk';

interface ITrainingProps extends ItrainigData {
    isUserTraining : boolean
}

const TrainingItem: React.FunctionComponent<ITrainingProps> = ({name, countDays, isUserTraining, id}) => {
    let percentAction = useAppSelector(state => state.training.today)
    
    let dispatch = useAppDispatch();

    const onClickHandler = () => dispatch(getArrDaysExpires(id))

    return (
        <Card style={{ width: '37rem', margin: "0 auto" }}>
          <Card.Body>
            <Card.Title>{name}</Card.Title>
            <Card.Subtitle className="mb-2 text-muted">Общее количество дней : {countDays}</Card.Subtitle>
            <Card.Text>
              Процент выполнения
            </Card.Text>
            <Card.Text >
              <ProgressBar animated style={{height : "27px"}} now={percentAction} label={`${percentAction}%`}/>
            </Card.Text>
            {isUserTraining ? <Button>Посмотреть упражнения на сегодня</Button> : <Button onClick={onClickHandler}>Посмотреть все упражения</Button>}
            <DayList/>
          </Card.Body>
        </Card>
      );
};

export default TrainingItem;
