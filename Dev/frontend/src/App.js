
import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import Dashboard from "./pages/Dashboard";
import DetalhesFiscal from "./pages/DetalhesFiscal";
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
      <Route path="/login" component={Login} />

      <Route exact path="/dashboard" component={Dashboard} />

      <Route exact path="/fiscal" component={Fiscal} />

      <Route path="/fiscal/detalhes/:id" component={DetalhesFiscal} />

      <Route path="/fiscal/editar/1/:id" component={FiscalCadastro1} />

      <Route path="/fiscal/cadastro/1" component={FiscalCadastro1} />

      <Route path="/fiscal/cadastro/2" component={FiscalCadastro2} />

      <Route path="/fiscal/cadastro/3" component={FiscalCadastro3} />

      <Route path="/fiscal/cadastro/3" component={FiscalCadastro3} />

      <Route path="/motorista/cadastro/1" component={CadastroMotorista1} />

      <Route path="/motorista/cadastro/2" component={CadastroMotorista2} />

      <Route path="/motorista/cadastro/3" component={CadastroMotorista3} />

      <Route path="/gerente/cadastro/1" component={CadastroGerente1} />

      <Route path="/gerente/cadastro/2" component={CadastroGerente2} />

      <Route path="/gerente/cadastro/3" component={CadastroGerente3} />

      <Route path="/admin/cadastro" component={CadastroAdmin} />

      <Route path="/linha/cadastro" component={CadastroParadaLinha} />

      <Route path="/onibus/cadastro" component={CadastroOnibus} />
    </Router>
  );
}

export default App;