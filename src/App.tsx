
import { Container } from 'react-bootstrap';
import {Route, Routes} from 'react-router-dom'
import './App.css';

import AuthForm from './components/auth/Auth';
import { authUser, registrationUser } from './slices/auth/thunk';
import AuthContainer from './components/auth/AuthContainer';

function App() {
  return (
    <div className="App">
      <Container>
        <Routes>
          <Route path="/auth">
            <Route path="login" element={<AuthContainer isRegistration={false}/>}/>
            <Route path="registration" element={<AuthContainer isRegistration={true}/>}/>
          </Route>
        </Routes>
      </Container>
    </div>
  );
}

export default App;
