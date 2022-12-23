import { IdishDescription } from "../../../../API/dishAPI/TdishAPI";
import RecomendedDishItem from './recomendedDishItem/RecomendedDishItem';


interface IRecomendedDishProps {
    recomendedDishArr : Array<IdishDescription>
}

const RecomendedDish: React.FunctionComponent<IRecomendedDishProps> = ({recomendedDishArr}) => {

    let dishCardArr = recomendedDishArr.map(el => <RecomendedDishItem {...el}/>)

    return (
        <>
            <h4>Предлагаемые блюда по фашим фильтрам</h4>
            {dishCardArr}
        </>
    )
};

export default RecomendedDish;
