import { Container } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useForm, SubmitHandler } from "react-hook-form";

type Inputs = {
    example: string,
    exampleRequired: string,
  };

function BasicExample() {

    const { register, handleSubmit, watch, formState: { errors } } = useForm<Inputs>();
    const onSubmit: SubmitHandler<Inputs> = data => {
        debugger
        console.log(data);
    }

    console.log(watch("example"));
    return (
        <Container>
        <Form onSubmit={handleSubmit(onSubmit)}>
            <Form.Group 
                className="mb-3" 
                controlId="formBasicEmail"
            >
                <Form.Label>Email address</Form.Label>
                <Form.Control 
                    type="text" 
                    placeholder="Enter email" 
                    {...register("example",{required:true, pattern : /\w{1,8}@\w{1,}/})} 
                />
                {errors.example && <span>This field is is not patter</span>}
                <Form.Text className="text-muted">
                    We'll never share your email with anyone else.
                </Form.Text>
            </Form.Group>

            <Form.Group 
                className="mb-3" 
                controlId="formBasicPassword"
            >
                <Form.Label>Password</Form.Label>
                <Form.Control
                    type="password" 
                    placeholder="Password" 
                    {...register("exampleRequired", { required: true })}
                />
            </Form.Group>
            {errors.exampleRequired && <span>This field is required</span> 
            
            }
            <Button 
                variant="primary" 
                type="submit"
            >
                Submit
            </Button>
        </Form>
        </Container>
    );
}

export default BasicExample;