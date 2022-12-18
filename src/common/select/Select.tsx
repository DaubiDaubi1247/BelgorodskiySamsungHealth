import Form from 'react-bootstrap/Form';

interface ISelectProps {
    valueArr : Array<string>
    onChangeHanler : (value : string) => void
}

const Select : React.FunctionComponent<ISelectProps> = ({valueArr,onChangeHanler}) => {

    let optionsArr = valueArr.map(el => <option value={el}>{el}</option>)

  return (
    <Form.Select aria-label="Default select example" onChange={e => onChangeHanler(e.target.value)}>
        {optionsArr}
    </Form.Select>
  );
}

export default Select;