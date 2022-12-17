
import { Card, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { DietsRoutes } from './../../../../../Routes/Routes';
import { useAppDispatch } from './../../../../../app/hooks';
import { setLoading } from '../../../../../slices/common/commonSlice';
import { setUserDiet } from '../../../../../slices/diets/thunk';

interface IDietCardProps {
    id : number
    label : string
    description : string
    userId? : number
}

const DietCard: React.FunctionComponent<IDietCardProps> = ({id, label, description,userId}) => {

    const navigate = useNavigate()
    const dispatch = useAppDispatch()

    const redirectToFullDescription = () => {
        dispatch(setLoading(true));
        if (userId) dispatch(setUserDiet({userId : userId, dietId : id}))
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
