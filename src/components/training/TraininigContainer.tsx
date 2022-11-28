
import { useAppSelector } from '../../app/hooks';
import { useEffect } from 'react';
import { useAppDispatch } from './../../app/hooks';
import { getUserTraining } from '../../slices/training/thunk';
import TrainingItem from './trainingItem/TrainingItem';
import SubscribeTraining from './subscribeTraining/SubscribeTrainig';

interface ITrainingContainerProps {
}

const TrainingContainer: React.FunctionComponent<ITrainingContainerProps> = (props) => {
    let userTraining = useAppSelector(state => state.training.smallUserTraining)
    let userId = useAppSelector(state => state.auth.accessData?.id)

    let dispatch = useAppDispatch()
    useEffect(() => {
        if (userId) {
            dispatch(getUserTraining(userId))
        }
    },[userId])

    return (
        <div>
            {userTraining ? <TrainingItem {...userTraining}/> : <SubscribeTraining/>}
        </div>
    );
};

export default TrainingContainer;
