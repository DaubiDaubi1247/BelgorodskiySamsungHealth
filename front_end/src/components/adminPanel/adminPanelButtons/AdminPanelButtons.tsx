import { Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import { AdminRoutes, DietsRoutes } from "../../../Routes/Routes";
import { useAppDispatch } from './../../../app/hooks';
import { getSmallDataAboutTrainings } from './../../../slices/training/thunk';
import { adminAPI } from './../../../API/adminAPI/adminAPI';
import { useState } from 'react';


interface AadminPanelButtonsProps {
}

const AdminPanelButtons: React.FunctionComponent<AadminPanelButtonsProps> = (props) => {
    const navigate = useNavigate();
    const dispatch = useAppDispatch()

    const [value, setvalue] = useState(0)

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

    const createDish = () => navigate(AdminRoutes.createDish)

    const downloadFileInJSON = () => {
        adminAPI.downloadInJSON(value)
            .then(response => {
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = 'employees.json';
                    a.click();
                });
            })
    }

    const downloadFileIn__ = () => {
        adminAPI.downloadInPDF(value)
            .then(response => {
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = 'employees.pdf';
                    a.click();
                });
            })
    }

    return (
        <>
            <Button style={{ marginRight: "15px" }} onClick={watchTraining}>Просмотреть тренировки</Button>
            <Button style={{ marginRight: "15px" }} onClick={createTraining}>Создать тренировку</Button>

            <Button style={{ marginRight: "15px" }} onClick={watchDiets}>Посмотреть все диеты</Button>
            <Button style={{ marginRight: "15px" }} onClick={createDiet}>Создать диету</Button>
            <Button style={{ marginRight: "15px" }} onClick={createDish}>Создать Блюдо</Button>

            <div className="" style={{ marginTop: "15px"}}>
                <h3>Для получения данных введите процент выполения</h3>
                <input style={{display : 'block', marginBottom : "10px"}} type="text" placeholder="Процент выполнения" value={value} onChange={e => setvalue(+e.target.value)}/>
                <Button style={{ marginRight: "15px" }} onClick={downloadFileInJSON}>Выгрузка данных в json</Button>
                <Button style={{ marginRight: "15px" }} onClick={downloadFileIn__}>Выгрузка данных в pdf</Button>
            </div>
        </>
    )
};

export default AdminPanelButtons;
