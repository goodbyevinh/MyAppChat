import React from 'react';
import './App.css';
import './styles/sb-admin-2.min.css'
import {BrowserRouter as Router} from 'react-router-dom'
import CustomRoutes from './routes/CustomRoutes';
import GlobalStyles from './GlobalStyles';

function App() {
  return (
    <GlobalStyles>
      <div className="App" id='wrapper'>
        <Router>
          <CustomRoutes/>
        </Router>
      </div>
    </GlobalStyles>

  );
}

export default App;
