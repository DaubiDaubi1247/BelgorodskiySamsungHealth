
import { Container } from 'react-bootstrap';

import './App.css';

import Header from './components/header/Header';

import MainContainer from './components/main/Main';
import { Route, Routes } from 'react-router-dom';
import { auth, AuthRoutes, main, MainRoutes } from './Routes/Routes';
import AuthContainer from './components/auth/AuthContainer';
import NavBar from './components/navBar/NavBar';
import TrainingContainer from './components/training/TraininigContainer';

function App() {

    return (
        <div className="App">
            <Header />
            <Container className='d-flex justify-content-center'>
                <NavBar />
                <div className='flex-fill'>
                    <Routes>
                        <Route path={main}>
                            <Route path={main} element={<MainContainer />} />
                            <Route path={MainRoutes.training} element={<TrainingContainer />} />
                        </Route>
                        <Route path={auth}>
                            <Route path={AuthRoutes.authRoute} element={<AuthContainer isRegistration={false} />} />
                            <Route path={AuthRoutes.registration} element={<AuthContainer isRegistration={true} />} />
                        </Route>
                    </Routes>
                </div>
            </Container>
        </div>
    );
}

export default App;
