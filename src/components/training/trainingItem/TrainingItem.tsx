
import { ItrainigData } from './../../../API/trainingAPI/TtrainingAPI';
import { useState } from 'react';


const TrainingItem: React.FunctionComponent<ItrainigData> = ({name, countDays}) => {

    const [isVisible, setVisible] = useState<Boolean>(false);
    const onClickHandler = () => setVisible(!isVisible)

  return (
    <div className="wrapper">
        <div onClick={onClickHandler} className="d-flex">
            <p>name : {name}</p>
            <p>Количество дней : {countDays}</p>
        </div>
        
    </div>
  );
};

export default TrainingItem;
