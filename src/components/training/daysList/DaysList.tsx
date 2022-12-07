import * as React from 'react';
import { ArrDaysExpires } from '../../../API/trainingAPI/TtrainingAPI';
import DayDescription from './dayDescription/DayDescription';

interface IDayListProps {
    trainingsArr: ArrDaysExpires
    isVisible : boolean
}

const DayList: React.FunctionComponent<IDayListProps> = ({ trainingsArr, isVisible }) => {

    const getAllTrainingsDay = () => trainingsArr.map(day => <DayDescription dayData={day} />)

    return (isVisible ? 
        <>
            {getAllTrainingsDay()}
        </>
        : <></>
    );
};

export default DayList;
