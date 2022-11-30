
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState } from 'react';
import { Card, ProgressBar } from 'react-bootstrap';
import { useAppSelector } from '../../../app/hooks';
import { Button } from 'react-bootstrap';

interface ITrainingProps extends ItrainigData {
    isUserTraining : boolean
}

const TrainingItem: React.FunctionComponent<ITrainingProps> = ({name, countDays, isUserTraining}) => {
    let percentAction = useAppSelector(state => state.training.today)

    const [isVisible, setVisible] = useState<Boolean>(false);
    const onClickHandler = () => setVisible(!isVisible)

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
            {isUserTraining ? <Button>Посмотреть упражнения на сегодня</Button> : <Button>Посмотреть все упражения</Button>}
          </Card.Body>
        </Card>
      );
};

export default TrainingItem;
