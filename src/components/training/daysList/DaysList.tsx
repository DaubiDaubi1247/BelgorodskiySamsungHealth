import * as React from 'react';
import DayDescription from './dayDescription/DayDescription';

interface IDayListProps {
    trainingsArr : Array<any>
}

const DayList: React.FunctionComponent<IDayListProps> = ({trainingsArr}) => {

    const getAllTrainingsDay = () => trainingsArr.map(day => <DayDescription {...day}/>)

    return <>
        {getAllTrainingsDay()}
    </>;
};

export default DayList;
