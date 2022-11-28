
import { Container } from 'react-bootstrap';
import {Route, Routes} from 'react-router-dom'
import './App.css';

import AuthForm from './components/auth/Auth';
import { authUser, registrationUser } from './slices/auth/thunk';
import AuthContainer from './components/auth/AuthContainer';
import Header from './components/header/Header';
import NavBar from './components/navBar/NavBar';

function App() {
  return (
    <div className="App">
      <Header/>
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
