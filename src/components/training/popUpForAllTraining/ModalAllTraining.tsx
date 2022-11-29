import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import TrainingItem from './../trainingItem/TrainingItem';

interface Iprops {
    show: boolean
    setShow: (show: boolean) => void
    trainigCardArr: Array<any>
}

const ModalAllTraining: React.FC<Iprops> = ({ show, setShow, trainigCardArr }) => {

    const handleShowAndClose = () => setShow(!show);

    return (
        <>
            <Modal show={show} onHide={handleShowAndClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Список доступных тренировок</Modal.Title>
                </Modal.Header>
                <Modal.Body>{trainigCardArr}</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleShowAndClose}>
                        Закрыть
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default ModalAllTraining