
import { Container } from 'react-bootstrap';
import {Route, Routes} from 'react-router-dom'
import './App.css';

import AuthForm from './components/auth/Auth';
import { authUser, registrationUser } from './slices/auth/thunk';
import AuthContainer from './components/auth/AuthContainer';
import Header from './components/header/Header';
import NavBar from './components/navBar/NavBar';
import Main from './components/main/Main';
import { auth, AuthRoutes, main, MainRoutes } from './Routes/Routes';
import TrainingContainer from './components/training/TraininigContainer';

function App() {
  return (
    <div className="App">
      <Header/>
      <Container className='d-flex'>
        <NavBar/>
        <div className="flex-fill">
            <Routes>
              <Route path={main}>
                <Route path={MainRoutes.training} element={<TrainingContainer/>}/>
              </Route>
              <Route path={auth}>
                <Route path={AuthRoutes.authRoute} element={<AuthContainer isRegistration={false}/>}/>
                <Route path={AuthRoutes.registration} element={<AuthContainer isRegistration={true}/>}/>
              </Route>
            </Routes>
        </div>
      </Container>
    </div>
  );
}

export default App;
