
import { useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { getSmallDataAboutDiets, getSmallDataAboutUserDiet } from './../../slices/diets/thunk';
import SubscribeDiet from './subscribeDiets/SubscribeDiets';
import withLoading from './../training/daysList/dayListContainer/DayListContainer';
import withAuthRedicrect from './../HOC/withAuthRedirect';
import { CONST } from '../../slices/common/Types';
import { stat } from 'fs';
import DietCard from './subscribeDiets/dietsList/dietCard/DietCard';
import { Button } from 'react-bootstrap';



interface IDietsContainerProps {
}

const DietsContainer: React.FunctionComponent<IDietsContainerProps> = (props) => {

    const [isVisible, setisVisible] = useState(false)

    let userHasDiet = useAppSelector(state => state.diets.userHasDiet)
    let smallDataUserDiet = useAppSelector(state => state.diets.smallDataAboutUserDiet)
    let userId = useAppSelector(state => state.auth.accessData?.id)

    const dispatch = useAppDispatch()

    useEffect(() => {
        if (userId) dispatch(getSmallDataAboutUserDiet(userId))
    },[userHasDiet])

    return (
        <div>
            {userHasDiet && smallDataUserDiet ? <DietCard {...smallDataUserDiet} userId={userId}/> : <></>}
            <Button style={{display : 'block', margin : "10px 0 10px 0"}} onClick={() => setisVisible(!isVisible)}>Посмотреть все диетыы</Button>
            {isVisible ? <SubscribeDiet userId={userId}/> : <></>}
        </div>
    )
};

export default withAuthRedicrect(DietsContainer);
