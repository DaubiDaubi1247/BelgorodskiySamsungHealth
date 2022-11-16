
import { Container } from 'react-bootstrap';
import {Route, Routes} from 'react-router-dom'
import './App.css';

import AuthForm from './components/auth/Auth';
import { authUser, registrationUser } from './slices/auth/thunk';

function App() {
  return (
    <div className="App">
      <Container>
        <Routes>
          <Route path="/auth" element={<AuthForm isRegistration={false} handlerForSubmit={authUser}/>}/>
          <Route path="/registration" element={<AuthForm isRegistration={true} handlerForSubmit={registrationUser}/>}/>
        </Routes>
      </Container>
    </div>
  );
}

export default App;
