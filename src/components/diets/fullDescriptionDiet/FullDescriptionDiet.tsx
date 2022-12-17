
import { useEffect } from 'react';
import { useAppSelector } from '../../../app/hooks';
import Select from './../../../common/select/Select';
import { useAppDispatch } from './../../../app/hooks';
import { setLoading } from '../../../slices/common/commonSlice';
import withLoading from './../../HOC/withLoading';
import { getMealsTimes, getTypes } from './../../../slices/dish/thunk';
import { Button } from 'react-bootstrap';

interface IFullDescriptionDietProps {
}

const FullDescriptionDiet: React.FunctionComponent<IFullDescriptionDietProps> = (props) => {

    let mealTimesArr = useAppSelector(state => state.dish.mealTimesArr)
    let mealTypesArr = useAppSelector(state => state.dish.mealTypesArr)
    let currentDiet = useAppSelector(state => state.diets.currentDietId)

    const dispatch = useAppDispatch()

    useEffect(() => {
        dispatch(setLoading(true))
        dispatch(getMealsTimes())
        dispatch(setLoading(true))
        dispatch(getTypes())
    })

    return (
        <div className='w-100' style={{margin : "10 auto"}}>
            <div>
                <p>Вы выбрали тренировку {currentDiet?.label}</p>
                <p>Для просмотра блюд выберите тип блюд и время приема пищи</p>
            </div>
            <div className="selectWrapper">
                <Select valueArr={mealTimesArr}/>
                <Select valueArr={mealTypesArr}/>
            </div>
            <Button>Просмотреть еду из данной диеты</Button>
        </div>
    )
};

export default withLoading(FullDescriptionDiet);
