
import { useAppSelector } from '../../app/hooks';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { useAppDispatch } from './../../app/hooks';
import AdminPanelButtons from './adminPanelButtons/AdminPanelButtons';

import withAuthRedicrect from './../HOC/withAuthRedirect';

interface IAdminPanelProps {
}

const AdminPanelContainer: React.FunctionComponent<IAdminPanelProps> = (props) => {

    let isAdmin = useAppSelector(state => state.auth.isAdmin)

    const navigate = useNavigate();
    const dispatch = useAppDispatch()

    if (!isAdmin) navigate("/")

    return (
        <div >
            <h2>Здравствуйте, Вы посетили панель администратора</h2>
            <AdminPanelButtons/>
        </div>
    )
};

export default withAuthRedicrect(AdminPanelContainer);
