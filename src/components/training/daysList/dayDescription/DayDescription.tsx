import * as React from 'react';
import { Button, Dropdown } from 'react-bootstrap';
import { DayExercises } from '../../../../API/trainingAPI/TtrainingAPI';
import { IdayDescription } from './../../../../API/trainingAPI/TtrainingAPI';
import ExcercisesInfo from './exerciseInfo/ExcercisesInfo';
import { useAppDispatch } from './../../../../app/hooks';

interface IDayDescriptionProps {
    dayData : IdayDescription
    currentDay? : number
}

const DayDescription: React.FunctionComponent<IDayDescriptionProps> = ({dayData,currentDay}) => {

    const isActiveDay = (day : number) => currentDay === day

    const isFinishedDay = (day : number) => currentDay ? currentDay > day : false

    const getAllexercisesForDay = () => dayData.sets.map(exercises => 
        <Dropdown.Item >
            <ExcercisesInfo {...exercises}/>
        </Dropdown.Item>
        
    )

    const dispatch = useAppDispatch()


    return (
        <Dropdown className='mb-4'>
            <Dropdown.Toggle  id="dropdown-button-dark-example1 " variant={isFinishedDay(dayData.numberOfDay) ? "success" : "secondary"} >
                День № {dayData.numberOfDay}
            </Dropdown.Toggle>

            <Dropdown.Menu variant="" show={isActiveDay(dayData.numberOfDay)}>
                {getAllexercisesForDay()}
                
                {/* <Button onClick={}>
                    Тренировки закончена
                </Button> */}
            </Dropdown.Menu>

        </Dropdown>
    );
};

export default DayDescription;
