import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import DetalhesFiscal from "./pages/DetalhesFiscal";

const App = () => {

  return (
    <Router>
      <Route path="/login">
         <Login/>
      </Route>
      <Route path='/fiscal'>
        <Fiscal/>
      </Route>
      <Route path='/fiscal-detalhes'>
        <DetalhesFiscal/>
      </Route>
    </Router>
  );
}

export default App;