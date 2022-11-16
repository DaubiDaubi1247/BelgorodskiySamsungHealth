import React from 'react';
import logo from './logo.svg';
import { Counter } from './features/counter/Counter';
import { Container } from 'react-bootstrap';
import {Route, Routes} from 'react-router-dom'
import './App.css';

import Auth from './components/auth/Auth';

function App() {
  return (
    <div className="App">
      <Container>
        <Routes>
          <Route path="/" element={<Auth isRegistration={false}/>}/>
          <Route path="/registration" element={<Auth isRegistration={true}/>}/>
        </Routes>
      </Container>
    </div>
  );
}

export default App;
