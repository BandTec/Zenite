import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import Dashboard from "./pages/Dashboard";

const App = () => {

  return (
    <Router>
      <Route path="/home">
         <Dashboard/>
      </Route>
      <Route path="/login">
         <Login/>
      </Route>
      <Route path='/fiscal'>
          <Fiscal/>
      </Route>
      <Route path='/dashboard'>
        <Dashboard/>
      </Route>  
    </Router>
  );
}

export default App;