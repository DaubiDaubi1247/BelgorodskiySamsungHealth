
import { Container } from 'react-bootstrap';
import {Route, Routes} from 'react-router-dom'
import './App.css';

import AuthForm from './components/auth/Auth';
import { authUser, registrationUser } from './slices/auth/thunk';
import AuthContainer from './components/auth/AuthContainer';
import Header from './components/header/Header';
import NavBar from './components/navBar/NavBar';
import Main from './components/main/Main';
import { auth, AuthRoutes, main } from './Routes/Routes';

function App() {
  return (
    <div className="App">
      <Header/>
      <Container>
        <Routes>
          <Route path={main} element={<Main/>}/>
          <Route path={auth}>
            <Route path={AuthRoutes.authRoute} element={<AuthContainer isRegistration={false}/>}/>
            <Route path={AuthRoutes.registration} element={<AuthContainer isRegistration={true}/>}/>
          </Route>
        </Routes>
      </Container>
    </div>
  );
}

export default App;
