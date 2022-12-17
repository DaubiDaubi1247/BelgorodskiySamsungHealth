
import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { getSmallDataAboutDiets } from './../../slices/diets/thunk';
import SubscribeDiet from './subscribeDiets/SubscribeDiets';
import withLoading from './../training/daysList/dayListContainer/DayListContainer';
import withAuthRedicrect from './../HOC/withAuthRedirect';


interface IDietsContainerProps {
}

const DietsContainer: React.FunctionComponent<IDietsContainerProps> = (props) => {

    let userHasDiet = useAppSelector(state => state.diets.userHasDiet)

    return (
        <>
            {userHasDiet ? <></> : <SubscribeDiet/>}
        </>
    )
};

export default withAuthRedicrect(DietsContainer);
