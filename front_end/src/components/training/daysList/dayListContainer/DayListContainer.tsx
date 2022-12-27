
import { useAppSelector } from '../../../../app/hooks';
import DayList from './../DaysList';
import withLoading from './../../../HOC/withAuthRedirect';
import { useEffect } from 'react';
import { useAppDispatch } from './../../../../app/hooks';
import { setErrorMsg } from '../../../../slices/training/trainingSlice';
import MessagefromServer from '../../../../common/messageFromServer/MessageFromServer';
import { ClickAwayListener } from '@material-ui/core';

interface IDayListContainerProps {
    isVisible: boolean
    isUserTraining: boolean
    setVisible: (value: boolean) => void
}

const DayListContainer: React.FunctionComponent<IDayListContainerProps> = ({ isVisible, isUserTraining, setVisible }) => {

    let trainingsArr = useAppSelector(state => state.training.arrDaysExpires)
    let currentDay = useAppSelector(state => state.training.currentDay)

    const dispatch = useAppDispatch()

    useEffect(() => function () {
        dispatch(setErrorMsg(null))
    }, [])

    const handleClick = () => setVisible(!isVisible);
    // useEffect(()=>{
    //     debugger
    //     document.addEventListener('onmousedown', handleClick)
    //     return () => document.removeEventListener('onmousedown', handleClick)
    // }, [])

    return (
        <div>
            {isVisible ?
                <DayList
                    trainingsArr={trainingsArr}
                    currentDay={currentDay}
                    isUserTraining={isUserTraining}
                />
                : <></>
            }
        </div>
    )
};

export default DayListContainer;
