import { IsmallDataAboutDietsArr } from "../../../../API/dietsAPI/TdietsAPI";
import DietCard from './dietCard/DietCard';


interface IDietsListProps {
    smallDataAboutDiet: IsmallDataAboutDietsArr
    userId? : number
}

const DietsList: React.FunctionComponent<IDietsListProps> = ({ smallDataAboutDiet,userId }) => {

    let dietCardList = smallDataAboutDiet.map(el => <DietCard {...el} userId={userId}/>)

    return (
        <>
            {dietCardList}
        </>
    )
};

export default DietsList;
