
import { Card, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { DietsRoutes } from './../../../../../Routes/Routes';

interface IDietCardProps {
    id : number
    label : string
    description : string
}

const DietCard: React.FunctionComponent<IDietCardProps> = ({id, label, description}) => {
    const navigate = useNavigate()

    const redirectToFullDescription = () => {
        navigate(DietsRoutes.fullDescription)
    }

    return (
        <Card style={{ width: '18rem' }}>
          <Card.Body>
            <Card.Title>{label}</Card.Title>
            <Card.Text>
              {description}
            </Card.Text>
            <Button variant="primary" onClick={redirectToFullDescription}>Посмотреть полностью</Button>
          </Card.Body>
        </Card>
      );
};

export default DietCard;
