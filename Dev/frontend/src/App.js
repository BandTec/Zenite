
import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import Dashboard from "./pages/Dashboard";
import DetalhesFiscal from "./pages/DetalhesFiscal";
// import FiscalEditar from "./pages/EditarFiscal/dadosPessoais";
import FiscalCadastro1 from "./pages/CadastroFiscal/dadosPessoais";
import FiscalCadastro2 from "./pages/CadastroFiscal/endereco";
import FiscalCadastro3 from "./pages/CadastroFiscal/dadosAcesso";
import CadastroMotorista1 from "./pages/CadastroMotorista/dadosPessoais";
import CadastroMotorista2 from "./pages/CadastroMotorista/endereco";
import CadastroMotorista3 from "./pages/CadastroMotorista/dadosAcesso";
import CadastroGerente1 from "./pages/CadastroGerente/dadosPessoais";
import CadastroGerente2 from "./pages/CadastroGerente/endereco";
import CadastroGerente3 from "./pages/CadastroGerente/dadosAcesso";
import CadastroAdmin from "./pages/CadastroAdmin";
import CadastroParadaLinha from "./pages/CadastroParadaLinha";
import CadastroOnibus from "./pages/CadastroOnibus";


const App = () => {

  return (
    <Router>
      <Route path="/login">
         <Login/>
      </Route>
      
      <Route path='/dashboard'>
        <Dashboard/>
      </Route>  
      
      <Route path='/fiscal'>
        <Fiscal/>
      </Route>

      <Route path='/fiscal-detalhes'>
        <DetalhesFiscal/>
      </Route>

    {/*   <Route path='/fiscal-editar'>
        <FiscalEditar/>
      </Route> */}

      <Route path="/cadastro1">
        <FiscalCadastro1/>
      </Route>

      <Route path="/cadastro2">
        <FiscalCadastro2 />
      </Route>

      <Route path="/cadastro3">
        <FiscalCadastro3 />
      </Route>
      <Route path="/cadastroMotorista1">
        <CadastroMotorista1 />
      </Route>

      <Route path="/cadastroMotorista2">
        <CadastroMotorista2 />
      </Route>

      <Route path="/cadastroMotorista3">
        <CadastroMotorista3 />
      </Route>
      
      <Route path="/cadastroGerente1">
        <CadastroGerente1 />
      </Route>

      <Route path="/cadastroGerente2">
        <CadastroGerente2 />
      </Route>

      <Route path="/cadastroGerente3">
        <CadastroGerente3 />
      </Route>
      
      <Route path="/cadastroAdmin">
        <CadastroAdmin />
      </Route>
    
      <Route path="/cadastroParadaLinha">
        <CadastroParadaLinha />
      </Route>

      <Route path="/cadastroOnibus">
        <CadastroOnibus />
      </Route>

    </Router>
  );
}

export default App;