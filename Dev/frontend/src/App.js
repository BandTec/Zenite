
import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import CadastroFiscal from "./pages/CadastroFiscal";

const App = () => {

  return (
    <Router>
      <Route path="/login">
         <Login/>
      </Route>
      
      <Route path='/fiscal'>
        <Fiscal/>
      </Route>

      <Route path="/cadastro">
        <CadastroFiscal/>
      </Route>

    </Router>
  );
}

export default App;