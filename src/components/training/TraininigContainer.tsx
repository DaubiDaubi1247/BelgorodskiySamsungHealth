
import { useAppSelector } from '../../app/hooks';
import { useEffect } from 'react';
import { useAppDispatch } from './../../app/hooks';
import { getUserTraining } from '../../slices/training/thunk';
import TrainingItem from './trainingItem/TrainingItem';
import SubscribeTraining from './subscribeTraining/SubscribeTrainig';
import { setLoading } from '../../slices/common/commonSlice';
import Preloader from '../../common/preloader/Preloader';

interface ITrainingContainerProps {
}

const TrainingContainer: React.FunctionComponent<ITrainingContainerProps> = (props) => {
    let userTraining = useAppSelector(state => state.training.smallUserTraining)
    let userId = useAppSelector(state => state.auth.accessData?.id)

    let isLoading = useAppSelector(state => state.common.isLoading)

    let dispatch = useAppDispatch()
    
    useEffect(() => {
        if (1) {
            dispatch(setLoading(true))
            dispatch(getUserTraining(1))
        }
    },[])

    return (isLoading ? <Preloader/> :
        <div>
            {userTraining ? <TrainingItem {...userTraining}/> : <SubscribeTraining/>}
        </div>
    );
};

export default TrainingContainer;
