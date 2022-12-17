import { useAppSelector } from "../../../app/hooks";
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { setLoading } from "../../../slices/common/commonSlice";
import { getSmallDataAboutDiets } from './../../../slices/diets/thunk';
import { useAppDispatch } from './../../../app/hooks';


interface ISubscribeDietProps {
}

const SubscribeDiet: React.FunctionComponent<ISubscribeDietProps> = (props) => {

    let smallDataAboutDiet = useAppSelector(state => state.diets.smallDataAboutDiets)
    
    const dispatch = useAppDispatch()

    useEffect(() => {
        dispatch(setLoading(true));
        dispatch(getSmallDataAboutDiets())
    })

  return (
    <>
        
    </>
  )
};

export default SubscribeDiet;
