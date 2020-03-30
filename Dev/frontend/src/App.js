
import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import DetalhesFiscal from "./pages/DetalhesFiscal";
import FiscalCadastro1 from "./pages/CadastroFiscal/dadosPessoais";
import FiscalCadastro2 from "./pages/CadastroFiscal/endereco";
 import FiscalCadastro3 from "./pages/CadastroFiscal/dadosAcesso";

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

      <Route path="/cadastro1">
        <FiscalCadastro1/>
      </Route>

      <Route path="/cadastro2">
        <FiscalCadastro2 />
      </Route>

      <Route path="/cadastro3">
        <FiscalCadastro3 />
      </Route>

    </Router>
  );
}

export default App;