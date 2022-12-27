
import { Card, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { DietsRoutes } from './../../../../../Routes/Routes';
import { useAppDispatch, useAppSelector } from './../../../../../app/hooks';
import { setLoading } from '../../../../../slices/common/commonSlice';
import { setUserDiet } from '../../../../../slices/diets/thunk';
import { setCurrentDietId } from '../../../../../slices/diets/diets';
import { IsmallDataAboutDiets } from '../../../../../API/dietsAPI/TdietsAPI';
import { dietsAPI } from './../../../../../API/dietsAPI/dietsAPI';

interface IDietCardProps {
    id : number
    label : string
    description : string
    userId? : number
}



const DietCard: React.FunctionComponent<IDietCardProps> = ({id, label, description,userId}) => {

    const navigate = useNavigate()
    const dispatch = useAppDispatch()

    let isAdmin = useAppSelector(state => state.auth.isAdmin)

    const userHasDiet = useAppSelector(state => state.diets.userHasDiet)

    const deactivateDiet = () => {
        dietsAPI.changeStatusDiet(id)
    }

    const redirectToFullDescription = () => {
        let dietData : IsmallDataAboutDiets = {
            id : id,
            label : label,
            description : description
        }
        dispatch(setCurrentDietId(dietData))

        //if (userId) dispatch(setUserDiet({userId : userId, dietId : id}))
        navigate(DietsRoutes.fullDescription)
    }

    const subscribeDiet = () => {
        if (userId) dispatch(setUserDiet({userId : userId, dietId : id}))
    }

    return (
        <Card style={{ width: '18rem' }}>
          <Card.Body>
            <Card.Title>{label}</Card.Title>
            <Card.Text>
              {description}
            </Card.Text>
            <Button variant="primary" onClick={redirectToFullDescription}>Посмотреть полностью</Button>
            {isAdmin ? <Button variant='danger' onClick={deactivateDiet}>x</Button> : <></>}
          </Card.Body>
        </Card>
      );
};

export default DietCard;
