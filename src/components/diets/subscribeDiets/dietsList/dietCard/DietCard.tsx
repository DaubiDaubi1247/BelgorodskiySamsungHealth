
import { Card, Button } from 'react-bootstrap';

interface IDietCardProps {
    id : number
    label : string
    description : string
}

const DietCard: React.FunctionComponent<IDietCardProps> = ({id, label, description}) => {
    return (
        <Card style={{ width: '18rem' }}>
          <Card.Body>
            <Card.Title>{label}</Card.Title>
            <Card.Text>
              {description}
            </Card.Text>
            <Button variant="primary">Go somewhere</Button>
          </Card.Body>
        </Card>
      );
};

export default DietCard;
