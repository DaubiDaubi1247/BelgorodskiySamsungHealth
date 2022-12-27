import * as React from 'react';
import { ArrDaysExpires } from '../../../API/trainingAPI/TtrainingAPI';
import { useAppSelector } from '../../../app/hooks';
import DayDescription from './dayDescription/DayDescription';
import MessagefromServer from './../../../common/messageFromServer/MessageFromServer';
import { useState } from 'react';

interface IDayListProps {
    trainingsArr: ArrDaysExpires
    currentDay? : number,
    isUserTraining : boolean
}

const DayList: React.FunctionComponent<IDayListProps> = ({ trainingsArr,currentDay,isUserTraining }) => {

    const getAllTrainingsDay = () => trainingsArr.map((day) => <DayDescription isUserTraining={isUserTraining} currentDay={currentDay} dayData={day} />)
    let errorMsg = useAppSelector(state => state.training.errorMsg)

    return ( 
        <div className='mt-4' >
            {errorMsg ? <MessagefromServer message={errorMsg} isError={true}/> :
            getAllTrainingsDay()
    }
        </div>
    );
};

export default DayList;
