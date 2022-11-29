
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState } from 'react';
import { Card, ProgressBar } from 'react-bootstrap';
import { useAppSelector } from '../../../app/hooks';


const TrainingItem: React.FunctionComponent<ItrainigData> = ({name, countDays}) => {
    let percentAction = useAppSelector(state => state.training.today)

    const [isVisible, setVisible] = useState<Boolean>(false);
    const onClickHandler = () => setVisible(!isVisible)

    return (
        <Card style={{ width: '18rem' }}>
          <Card.Body>
            <Card.Title>{name}</Card.Title>
            <Card.Subtitle className="mb-2 text-muted">Общее количество дней : {countDays}</Card.Subtitle>
            <Card.Text>
              Процент выполнения
            </Card.Text>
            <Card.Text>
              <ProgressBar style={{height : "27px"}} now={percentAction} label={`${percentAction}%`}/>
            </Card.Text>
            <Card.Link href="#">Card Link</Card.Link>
            <Card.Link href="#">Another Link</Card.Link>
          </Card.Body>
        </Card>
      );
};

export default TrainingItem;
