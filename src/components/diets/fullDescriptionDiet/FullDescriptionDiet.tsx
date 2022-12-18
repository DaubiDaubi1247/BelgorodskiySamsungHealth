
import { useEffect, useState } from 'react';
import { useAppSelector } from '../../../app/hooks';
import Select from './../../../common/select/Select';
import { useAppDispatch } from './../../../app/hooks';
import { setLoading } from '../../../slices/common/commonSlice';
import withLoading from './../../HOC/withLoading';
import { getMealsTimes, getTypes } from './../../../slices/dish/thunk';
import { Button } from 'react-bootstrap';
import { getMealsByFilter } from './../../../slices/diets/thunk';
import { Ifilters } from '../../../API/dietsAPI/TdietsAPI';
import RecomendedDish from './recomendedDIsh/RecomendenDish';
import FormError from '../../../common/form/formError/FormError';
import withAuthRedicrect from './../../main/Main';

interface IFullDescriptionDietProps {
}

const FullDescriptionDiet: React.FunctionComponent<IFullDescriptionDietProps> = (props) => {

    const [typeOfMeal, settypeOfMeal] = useState("")
    const [mealTime, setmealTime] = useState("");
    const [isVisible, setisVisible] = useState(false)

    let mealTimesArr = useAppSelector(state => state.dish.mealTimesArr)
    let mealTypesArr = useAppSelector(state => state.dish.mealTypesArr)
    let currentDiet = useAppSelector(state => state.diets.currentDietId)
    let recomendedDishArr = useAppSelector(state => state.diets.recomendedDishArr)
    let dietError = useAppSelector(state => state.diets.dietsError)

    const dispatch = useAppDispatch()
    
    useEffect(() => {   
        dispatch(setLoading(true))
        dispatch(getTypes())
    },[])

    useEffect(() => {
        dispatch(getMealsTimes())
    },[])


    const getMealsByFilterHanlder = () => {
        const filters : Ifilters = {
            dietId : currentDiet?.id,
            typeOfMeal : typeOfMeal,
            mealTime : mealTime
        }

        debugger
        dispatch(getMealsByFilter(filters))
        setisVisible(true)
    }

    return (
        <div className='w-100' style={{margin : "10 auto"}}>
            <div>
                <p>Вы выбрали тренировку {currentDiet?.label}</p>
                <p>Для просмотра блюд выберите тип блюд и время приема пищи</p>
            </div>
            <div className="selectWrapper">
                <Select valueArr={mealTimesArr} onChangeHanler={setmealTime}/>
                <Select valueArr={mealTypesArr} onChangeHanler={settypeOfMeal}/>
            </div>
            <Button onClick={getMealsByFilterHanlder}>Просмотреть еду из данной диеты</Button>
            {isVisible ? <RecomendedDish recomendedDishArr={recomendedDishArr}/> : <></>}
            {dietError.length !== 0 ? <FormError message={dietError} /> : <></>}
        </div>
    )
};

export default withAuthRedicrect(FullDescriptionDiet);
