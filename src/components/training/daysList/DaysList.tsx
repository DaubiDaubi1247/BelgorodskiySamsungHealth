import * as React from 'react';
import DayDescription from './dayDescription/DayDescription';

interface IDayListProps {
    trainingsArr: Array<any>
    isVisible : boolean
}

const DayList: React.FunctionComponent<IDayListProps> = ({ trainingsArr, isVisible }) => {

    const getAllTrainingsDay = () => trainingsArr.map(day => <DayDescription {...day} />)

    return (isVisible ? 
        <>
            {getAllTrainingsDay()}
        </>
        : <></>
    );
};

export default DayList;
