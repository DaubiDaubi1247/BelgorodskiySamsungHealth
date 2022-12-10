import * as React from 'react';
import { Dropdown } from 'react-bootstrap';
import { DayExercises } from '../../../../API/trainingAPI/TtrainingAPI';
import { IdayDescription } from './../../../../API/trainingAPI/TtrainingAPI';
import ExcercisesInfo from './exerciseInfo/ExcercisesInfo';

interface IDayDescriptionProps {
    dayData : IdayDescription
}

const DayDescription: React.FunctionComponent<IDayDescriptionProps> = ({dayData}) => {

    const isActiveDay = (day : number) => dayData.numberOfDay === day

    const getAllexercisesForDay = () => dayData.sets.map((exercises) => 
        <Dropdown.Item >
            <ExcercisesInfo {...exercises}/>
        </Dropdown.Item>
        
    )

    return (
        <Dropdown className='mb-4'>
            <Dropdown.Toggle  id="dropdown-button-dark-example1 " variant="secondary" active={isActiveDay(dayData.numberOfDay)}>
                День № {dayData.numberOfDay}
            </Dropdown.Toggle>

            <Dropdown.Menu variant="">
                {getAllexercisesForDay()}
                {/* <Dropdown.Item href="#/action-1" active>
                    Actions
                </Dropdown.Item>
                <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
                <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
                <Dropdown.Divider />
                <Dropdown.Item href="#/action-4">Separated link</Dropdown.Item> */}
            </Dropdown.Menu>
        </Dropdown>
    );
};

export default DayDescription;
