import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { IuserDataForSet } from '../../../../API/userAPI/TuserAPI';
import ModalForm from './modalForm/ModalForm';
import { IuserIS } from './../../../../slices/user/Types';

interface IAppProps {
    show: boolean
    handleShowAndClose: () => void
    setUserData: (userData: IuserDataForSet) => void
    userId? : number
    userData : IuserIS
}

const ModalForSetUserData: React.FunctionComponent<IAppProps> = ({ show, handleShowAndClose, setUserData,userId,userData }) => {

    return (
        <>
            <Modal show={show} onHide={handleShowAndClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Заполните форму</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <ModalForm 
                        setUserData={setUserData}
                        userId={userId}
                        userData={userData}
                        handleShowAndClose={handleShowAndClose}
                    />
                </Modal.Body>
            </Modal>
        </>
    );
};



export default ModalForSetUserData