import { IdishDescription } from "../../../../../API/dishAPI/TdishAPI";
import { Card } from 'react-bootstrap';


interface IRecomendedDishItemProps {
}

const RecomendedDishItem: React.FunctionComponent<IdishDescription> = ({label, type, calPerGram, proteinsPerGram, fatsPerGram}) => {
    return (
        <Card style={{ width: '18rem' }}>
          <Card.Body>
            <Card.Title>{label}</Card.Title>
            <Card.Text>
              Тип блюда : {type}
            </Card.Text>
            <Card.Text>
              Калории : {calPerGram} гр
            </Card.Text>
            <Card.Text>
              Протеин : {proteinsPerGram} гр
            </Card.Text>
            <Card.Text>
              Жиры : {fatsPerGram} гр
            </Card.Text>
          </Card.Body>
        </Card>
    )
};

export default RecomendedDishItem;
