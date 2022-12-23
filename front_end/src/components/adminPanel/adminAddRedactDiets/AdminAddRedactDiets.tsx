
import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from './../../../app/hooks';
import { getSmallDataAboutDiets } from './../../../slices/diets/thunk';
import DietsList from './../../diets/subscribeDiets/dietsList/DietsList';

interface AadminAddRedactDietsProps {
}

const AdminAddRedactDiets: React.FunctionComponent<AadminAddRedactDietsProps> = (props) => {
    const dispatch = useAppDispatch()

    let smallDataAboutDiet = useAppSelector(state => state.diets.smallDataAboutDiets)

    useEffect(() => {
        dispatch(getSmallDataAboutDiets())
    },[])

    return (
        <>
            <DietsList
                smallDataAboutDiet={smallDataAboutDiet}
            />
        </>
    )
};

export default AdminAddRedactDiets;
