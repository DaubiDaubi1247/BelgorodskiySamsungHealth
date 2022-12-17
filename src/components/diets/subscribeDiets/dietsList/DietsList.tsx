import { IsmallDataAboutDietsArr } from "../../../../API/dietsAPI/TdietsAPI";
import DietCard from './dietCard/DietCard';


interface IDietsListProps {
    smallDataAboutDiet: IsmallDataAboutDietsArr
}

const DietsList: React.FunctionComponent<IDietsListProps> = ({ smallDataAboutDiet }) => {

    let dietCardList = smallDataAboutDiet.map(el => <DietCard {...el}/>)

    return (
        <>
            {dietCardList}
        </>
    )
};

export default DietsList;
