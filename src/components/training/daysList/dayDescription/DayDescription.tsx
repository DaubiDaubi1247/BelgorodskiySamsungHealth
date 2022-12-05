import * as React from 'react';
import { Dropdown } from 'react-bootstrap';

interface IDayDescriptionProps {
    exercisesDayArr : Array<any>
}

const DayDescription: React.FunctionComponent<IDayDescriptionProps> = ({exercisesDayArr}) => {

    const getAllexercisesForDay = () => exercisesDayArr.map(exercises => <Dropdown.Item>{exercises}</Dropdown.Item>)

    return (
        <Dropdown>
            <Dropdown.Toggle id="dropdown-button-dark-example1" variant="secondary">
                Dropdown Button
            </Dropdown.Toggle>

            <Dropdown.Menu variant="dark">
                {getAllexercisesForDay()}
                {/* <Dropdown.Item href="#/action-1" active>
                    Action
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
