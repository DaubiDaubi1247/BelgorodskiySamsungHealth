
import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './../../app/hooks';
import { getSmallDataAboutDiets } from './../../slices/diets/thunk';
import SubscribeDiet from './subscribeDiets/SubscribeDiets';
interface IDietsContainerProps {
}

const DietsContainer: React.FunctionComponent<IDietsContainerProps> = (props) => {

    let userHasDiet = useAppSelector(state => state.diets.userHasDiet)
    let dispatch = useAppDispatch()

    return (
        <>
            {userHasDiet ? <></> : <SubscribeDiet/>}
        </>
    )
};

export default DietsContainer;
