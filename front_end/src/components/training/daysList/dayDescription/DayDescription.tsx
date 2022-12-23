import * as React from 'react';
import { Button, Dropdown } from 'react-bootstrap';
import { DayExercises } from '../../../../API/trainingAPI/TtrainingAPI';
import { IdayDescription } from './../../../../API/trainingAPI/TtrainingAPI';
import ExcercisesInfo from './exerciseInfo/ExcercisesInfo';
import { useAppDispatch } from './../../../../app/hooks';
import { useState } from 'react';

interface IDayDescriptionProps {
    dayData : IdayDescription
    currentDay? : number
    isUserTraining? : boolean
}

const DayDescription: React.FunctionComponent<IDayDescriptionProps> = ({dayData,currentDay,isUserTraining}) => {

    const [isVisible, setisVisible] = useState(true)

    const isActiveDay = (day : number) => isUserTraining && currentDay === day

    const isFinishedDay = (day : number) => isUserTraining && currentDay ? currentDay > day : false

    const getAllexercisesForDay = () => dayData.sets.map(exercises => 
        <Dropdown.Item >
            <ExcercisesInfo {...exercises}/>
        </Dropdown.Item>
        
    )

    const dispatch = useAppDispatch()


    return (
        <Dropdown className='mb-4'>
            <Dropdown.Toggle  id="dropdown-button-dark-example1 " variant={isFinishedDay(dayData.numberOfDay) ? "success" : "secondary"} onBlur={() => setisVisible(false)}>
                День № {dayData.numberOfDay}
            </Dropdown.Toggle>

            <Dropdown.Menu variant="" show={isVisible && isActiveDay(dayData.numberOfDay)}>
                {getAllexercisesForDay()}
            </Dropdown.Menu>

        </Dropdown>
    );
};

export default DayDescription;
