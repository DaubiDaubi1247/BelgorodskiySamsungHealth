import { Exercises } from "../../../../../API/trainingAPI/TtrainingAPI";


const ExcercisesInfo: React.FunctionComponent<Exercises> = (props) => {
  return (
    <>
        <p>Упражение : {props.exercise.label}</p>
        <p>Описание : {props.exercise.description}</p>
        <p>Колво повторений : {props.numberOfRepetitions}</p>
        <p>Отдых : {props.restTimeInSec} с</p>
    </>
  )
};

export default ExcercisesInfo;
