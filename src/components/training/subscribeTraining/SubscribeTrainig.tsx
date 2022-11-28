import styles from "../subscribeTraining/style.module.css"

interface ISubscribeTrainingProps {
}

const SubscribeTraining: React.FunctionComponent<ISubscribeTrainingProps> = (props) => {
  return (
    <div className={"d-flex " + styles.wrapper}>
        <span className="d-block">Кажется Вы не подписаны на тренировку...</span>
        <button className='btn btn-primary btn-lg'>Подписаться на тренировку</button>
    </div>
  )
};

export default SubscribeTraining;
