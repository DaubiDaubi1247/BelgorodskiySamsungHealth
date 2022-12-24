import Form from 'react-bootstrap/Form';

interface ISelectProps {
    valueArr : Array<string>
    onChangeHanler : (value : string) => void
    isEmpty : boolean
}

const Select : React.FunctionComponent<ISelectProps> = ({valueArr,onChangeHanler,isEmpty}) => {

    let optionsArr = valueArr.map(el => <option value={el}>{el}</option>)
    if (isEmpty) {
        onChangeHanler(valueArr[0])}

  return (
    <Form.Select style={{width : "50%"}} aria-label="Default select example" onChange={e => {onChangeHanler(e.target.value)}}>
        {optionsArr}
    </Form.Select>
  );
}

export default Select;