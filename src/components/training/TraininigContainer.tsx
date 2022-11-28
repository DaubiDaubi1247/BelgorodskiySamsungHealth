
import { useAppSelector } from '../../app/hooks';
import { useEffect } from 'react';
import { useAppDispatch } from './../../app/hooks';
import { getUserTraining } from '../../slices/training/thunk';

interface ITrainingContainerProps {
}

const TrainingContainer: React.FunctionComponent<ITrainingContainerProps> = (props) => {
    let userTraining = useAppSelector(state => state.training.smallUserTraining)
    let userId = useAppSelector(state => state.auth.accessData.id)

    useEffect(() => {
        let dispatch = useAppDispatch()
        dispatch(getUserTraining(userId))
    })
    return (
        <div>
            {userTraining ? <button className='btn btn-primary btn-lg'>Подписаться на тренировку</button> : <></>}
        </div>
    );
};

export default TrainingContainer;
