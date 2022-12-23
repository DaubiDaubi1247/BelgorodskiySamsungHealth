import * as React from 'react';
import { ArrDaysExpires } from '../../../API/trainingAPI/TtrainingAPI';
import DayDescription from './dayDescription/DayDescription';

interface IDayListProps {
    trainingsArr: ArrDaysExpires
    isVisible : boolean
    currentDay? : number,
    isUserTraining : boolean
}

const DayList: React.FunctionComponent<IDayListProps> = ({ trainingsArr, isVisible,currentDay,isUserTraining }) => {

    const getAllTrainingsDay = () => trainingsArr.map((day, index) => <DayDescription isUserTraining={isUserTraining} currentDay={currentDay} dayData={day} />)

    return (isVisible ? 
        <div className='mt-4'>
            {getAllTrainingsDay()}
        </div>
        : <></>
    );
};

export default DayList;
