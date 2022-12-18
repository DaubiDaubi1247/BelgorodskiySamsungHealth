
import { useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { getSmallDataAboutDiets } from './../../slices/diets/thunk';
import SubscribeDiet from './subscribeDiets/SubscribeDiets';
import withLoading from './../training/daysList/dayListContainer/DayListContainer';
import withAuthRedicrect from './../HOC/withAuthRedirect';
import { CONST } from '../../slices/common/Types';


interface IDietsContainerProps {
}

const DietsContainer: React.FunctionComponent<IDietsContainerProps> = (props) => {

    let userHasDiet = useAppSelector(state => state.diets.userHasDiet)
    let userId = useAppSelector(state => state.auth.accessData?.id)

    return (
        <>
            {userHasDiet ? <></> : <SubscribeDiet userId={userId}/>}
        </>
    )
};

export default withAuthRedicrect(DietsContainer);
