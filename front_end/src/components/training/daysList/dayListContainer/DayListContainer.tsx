import * as React from 'react';
import { useAppSelector } from '../../../../app/hooks';
import DayList from './../DaysList';
import withLoading from './../../../HOC/withAuthRedirect';

interface IDayListContainerProps {
    isVisible: boolean
    isUserTraining : boolean
}

const DayListContainer: React.FunctionComponent<IDayListContainerProps> = ({ isVisible,isUserTraining }) => {

    let trainingsArr = useAppSelector(state => state.training.arrDaysExpires)
    let currentDay = useAppSelector(state => state.training.currentDay)
    // let trainingsArr = arrDaysExpires.filter(el => el !== undefined)
    return (
        <DayList
            isVisible={isVisible}
            trainingsArr={trainingsArr}
            currentDay={currentDay}
            isUserTraining={isUserTraining}
        />
    )
};

export default withLoading(DayListContainer);
