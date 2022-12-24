
import { useAppSelector } from '../../../../app/hooks';
import DayList from './../DaysList';
import withLoading from './../../../HOC/withAuthRedirect';
import { useEffect } from 'react';
import { useAppDispatch } from './../../../../app/hooks';
import { setErrorMsg } from '../../../../slices/training/trainingSlice';
import MessagefromServer from '../../../../common/messageFromServer/MessageFromServer';

interface IDayListContainerProps {
    isVisible: boolean
    isUserTraining : boolean
}

const DayListContainer: React.FunctionComponent<IDayListContainerProps> = ({ isVisible,isUserTraining }) => {

    let trainingsArr = useAppSelector(state => state.training.arrDaysExpires)
    let currentDay = useAppSelector(state => state.training.currentDay)
    let errorMsg = useAppSelector(state => state.training.errorMsg)

    const dispatch = useAppDispatch()

    useEffect(() => function() {
        dispatch(setErrorMsg(null))
    })
    
    return (
        <div className="">
            {errorMsg ? <MessagefromServer message={errorMsg} isError={true}/> : 
            <DayList
                isVisible={isVisible}
                trainingsArr={trainingsArr}
                currentDay={currentDay}
                isUserTraining={isUserTraining}
            />
    }
        </div>
    )
};

export default withLoading(DayListContainer);
