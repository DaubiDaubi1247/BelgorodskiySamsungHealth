
import { useAppSelector } from '../../app/hooks';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { useAppDispatch } from './../../app/hooks';
import AdminPanelButtons from './adminPanelButtons/AdminPanelButtons';

interface IAdminPanelProps {
}

const AdminPanelContainer: React.FunctionComponent<IAdminPanelProps> = (props) => {

    let isAdmin = useAppSelector(state => state.auth.isAdmin)

    const navigate = useNavigate();
    const dispatch = useAppDispatch()

    useEffect(() => {
        // navigate to error page
        //if (!isAdmin) navigate()
        //dispatch(getAllTra)

    })

    return (
        <div style={{width : "90%", margin:"0 auto"}}>
            <h2>Здравствуйте, Вы посетили панель администратора</h2>
            <AdminPanelButtons/>
            
        </div>
    )
};

export default AdminPanelContainer;
