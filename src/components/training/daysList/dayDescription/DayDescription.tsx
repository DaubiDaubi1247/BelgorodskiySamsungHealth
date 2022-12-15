import * as React from 'react';
import { Dropdown } from 'react-bootstrap';
import { DayExercises } from '../../../../API/trainingAPI/TtrainingAPI';
import { IdayDescription } from './../../../../API/trainingAPI/TtrainingAPI';
import ExcercisesInfo from './exerciseInfo/ExcercisesInfo';

interface IDayDescriptionProps {
    dayData : IdayDescription
    currentDay? : number
}

const DayDescription: React.FunctionComponent<IDayDescriptionProps> = ({dayData,currentDay}) => {

    const isActiveDay = (day : number) => currentDay === day

    console.log(dayData.sets)
    const getAllexercisesForDay = () => dayData.sets.map((exercises) => 
        <Dropdown.Item >
            <ExcercisesInfo {...exercises}/>
        </Dropdown.Item>
        
    )

    return (
        <Dropdown className='mb-4'>
            <Dropdown.Toggle  id="dropdown-button-dark-example1 " variant="secondary" >
                День № {dayData.numberOfDay}
            </Dropdown.Toggle>

            <Dropdown.Menu variant="" show={isActiveDay(dayData.numberOfDay)}>
                {getAllexercisesForDay()}
            </Dropdown.Menu>
        </Dropdown>
    );
};

export default DayDescription;
