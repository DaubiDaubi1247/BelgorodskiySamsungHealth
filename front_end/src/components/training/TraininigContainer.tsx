
import { useAppSelector } from '../../app/hooks';
import { useEffect } from 'react';
import { useAppDispatch } from './../../app/hooks';
import { getUserTraining } from '../../slices/training/thunk';
import TrainingItem from './trainingItem/TrainingItem';
import SubscribeTraining from './subscribeTraining/SubscribeTrainig';
import { setLoading } from '../../slices/common/commonSlice';
import Preloader from '../../common/preloader/Preloader';
import withAuthRedicrect from './../HOC/withAuthRedirect';

interface ITrainingContainerProps {
}

const TrainingContainer: React.FunctionComponent<ITrainingContainerProps> = (props) => {
    let userTraining = useAppSelector(state => state.training.smallUserTraining)
    let userId = useAppSelector(state => state.auth.accessData?.id)
    let userHasTraining = useAppSelector(state => state.training.userHasTraining)

    let isLoading = useAppSelector(state => state.common.isLoading)

    let dispatch = useAppDispatch()

    useEffect(() => {
        if (userId) {
            debugger
            dispatch(setLoading(true))
            dispatch(getUserTraining(userId))
        }
    },[userHasTraining])

    return (isLoading ? <Preloader/> :
        <div>
            {userHasTraining && userTraining ? <TrainingItem {...userTraining} isUserTraining={true}/> : <></>}
            <SubscribeTraining isUserTrain={false}/>
        </div>
    );
};

export default withAuthRedicrect(TrainingContainer)