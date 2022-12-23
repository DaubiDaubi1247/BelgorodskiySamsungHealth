import * as React from 'react';
import { Card, ProgressBar } from 'react-bootstrap';

interface IProgressbarProps {
    isUserTraining: boolean
    percentAction: number
    text: string
}

const Progressbar: React.FunctionComponent<IProgressbarProps> = ({isUserTraining, percentAction, text}) => {
    return (isUserTraining ? 
        <>
            <Card.Text>
                {text}
            </Card.Text>
            <Card.Text >
                <ProgressBar animated style={{ height: "27px" }} now={percentAction} label={`${percentAction}%`} />
            </Card.Text>
        </>
    : <></>
    )
};

export default Progressbar;
