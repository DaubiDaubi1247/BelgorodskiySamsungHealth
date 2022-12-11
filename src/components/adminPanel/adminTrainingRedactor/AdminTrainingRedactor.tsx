import { useAppSelector } from "../../../app/hooks";
import TrainingItem from './../../training/trainingItem/TrainingItem';

interface IAdminTrainingRedactorProps {
}

const AdminTrainingRedactor: React.FunctionComponent<IAdminTrainingRedactorProps> = (props) => {

    let smallDataAboutTRainings = useAppSelector(state => state.training.smallDataTrainings)

    const trainingItemArr = smallDataAboutTRainings.map(el => <TrainingItem {...el} isAdmin isUserTraining={false}/>)

    return <>
        {trainingItemArr}
    </>
};

export default AdminTrainingRedactor;
