import { useAppSelector } from "../../../app/hooks";
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { setLoading } from "../../../slices/common/commonSlice";
import { getSmallDataAboutDiets } from './../../../slices/diets/thunk';
import { useAppDispatch } from './../../../app/hooks';
import DietsList from "./dietsList/DietsList";


interface ISubscribeDietProps {
    userId? : number
}

const SubscribeDiet: React.FunctionComponent<ISubscribeDietProps> = ({userId}) => {

    //let smallDataAboutDiets = useAppSelector(state => state.diets.smallDataAboutDiets)
    let smallDataAboutDiets : any[] = [];

    const dispatch = useAppDispatch()

    useEffect(() => {
        dispatch(setLoading(true));
        dispatch(getSmallDataAboutDiets())
    },[])

  return (
    <>
        <DietsList userId={userId} smallDataAboutDiet={smallDataAboutDiets}/>
    </>
  )
};

export default SubscribeDiet;
