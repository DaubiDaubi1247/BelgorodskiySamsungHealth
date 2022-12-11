import { Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import { AdminRoutes } from "../../../Routes/Routes";
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

    return (
        <>
            <Button style={{marginRight:"15px"}} onClick={watchTraining}>Просмотреть тренировки</Button>
            <Button>Создать тренировку</Button>
        </>
    )
};

export default AdminPanelButtons;
