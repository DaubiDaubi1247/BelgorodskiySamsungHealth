
import { Container } from 'react-bootstrap';

import './App.css';

import Header from './components/header/Header';

import MainContainer from './components/main/Main';
import { Route, Routes } from 'react-router-dom';
import { auth, AuthRoutes, main, MainRoutes, admin, AdminRoutes, profile, diets } from './Routes/Routes';
import AuthContainer from './components/auth/AuthContainer';
import NavBar from './components/navBar/NavBar';
import TrainingContainer from './components/training/TraininigContainer';
import AdminPanelContainer from './components/adminPanel/AdminPanel';
import AdminTrainingRedactor from './components/adminPanel/adminTrainingRedactor/AdminTrainingRedactor';
import AdminAddTrainingMenu from './components/adminPanel/adminAddTrainingMenu/AdminAddTrainingMenu';
import ProfileContainer from './components/profile/ProfileContainer';

function App() {

    return (
        <div className="App">
            <Header />
            <Container className='d-flex'>
                <NavBar />
                <div className='flex-fill d-flex justify-content-center'>
                    <Routes>
                        <Route path={main}>
                            <Route path={main} element={<MainContainer />} />
                            <Route path={MainRoutes.training} element={<TrainingContainer />} />
                        </Route>
                        <Route path={auth}>
                            <Route path={AuthRoutes.authRoute} element={<AuthContainer isRegistration={false} />} />
                            <Route path={AuthRoutes.registration} element={<AuthContainer isRegistration={true} />} />
                        </Route>
                        <Route path={admin}>
                            <Route path={admin} element={<AdminPanelContainer />} />
                            <Route path={AdminRoutes.redactOfTrain} element={<AdminTrainingRedactor/>}/>
                            <Route path={AdminRoutes.createTraining} element={<AdminAddTrainingMenu/>}/>
                        </Route>
                        <Route path={profile} element={<ProfileContainer/>}/>
                        <Route path={diets} element={<ProfileContainer/>}>

                        </Route>
                    </Routes>
                </div>
            </Container>
        </div>
    );
}

export default App;
