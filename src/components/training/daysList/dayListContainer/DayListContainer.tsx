import * as React from 'react';
import { useAppSelector } from '../../../../app/hooks';
import DayList from './../DaysList';
import withLoading from './../../../HOC/withAuthRedirect';

interface IDayListContainerProps {
    isVisible: boolean
}

const DayListContainer: React.FunctionComponent<IDayListContainerProps> = ({ isVisible }) => {

    let trainingsArr = useAppSelector(state => state.training.arrDaysExpires)
    return (
        <DayList
            isVisible={isVisible}
            trainingsArr={trainingsArr}
        />
    )
};

export default withLoading(DayListContainer);
