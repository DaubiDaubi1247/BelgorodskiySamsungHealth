import Form from 'react-bootstrap/Form';

interface ISelectProps {
    valueArr : Array<string>
}

const Select : React.FunctionComponent<ISelectProps> = ({valueArr}) => {

    let optionsArr = valueArr.map(el => <option value={el}>{el}</option>)

  return (
    <Form.Select aria-label="Default select example">
        {optionsArr}
    </Form.Select>
  );
}

export default Select;