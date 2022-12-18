import { Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import { AdminRoutes, DietsRoutes } from "../../../Routes/Routes";
import { useAppDispatch } from './../../../app/hooks';
import { getSmallDataAboutTrainings } from './../../../slices/training/thunk';


interface AadminPanelButtonsProps {
}

const AdminPanelButtons: React.FunctionComponent<AadminPanelButtonsProps> = (props) => {
    const navigate = useNavigate();
    const dispatch = useAppDispatch()

    const watchTraining = () => {
        dispatch(getSmallDataAboutTrainings())
        navigate(AdminRoutes.redactOfTrain);
    }

    const watchDiets = () => {
        dispatch(getSmallDataAboutTrainings())
        navigate(AdminRoutes.redactDiets);
    }

    const createTraining = () => navigate(AdminRoutes.createTraining)

    const createDiet = () => navigate(AdminRoutes.createDiets)

    return (
        <>
            <Button style={{marginRight:"15px"}} onClick={watchTraining}>Просмотреть тренировки</Button>
            <Button style={{marginRight:"15px"}} onClick={createTraining}>Создать тренировку</Button>

            <Button style={{marginRight:"15px"}} onClick={watchDiets}>Посмотреть все диеты</Button>
            <Button style={{marginRight:"15px"}} onClick={createDiet}>Создать диету</Button>
        </>
    )
};

export default AdminPanelButtons;
