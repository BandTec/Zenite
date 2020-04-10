
import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import Linha from "./pages/VisualizaLinha";
import Motorista from "./pages/VisualizaMotorista";
import Dashboard from "./pages/Dashboard";
import DetalhesFiscal from "./pages/DetalhesFiscal";
import CadastroFiscal from "./pages/CadastroFiscal";
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
import MenuLateral from "./components/MenuLateral";

const App = () => {
  return (
    <Router>
      <MenuLateral />

      <Route path="/login" component={Login} />

      <div className="conteudo">
        <Route exact path="/dashboard" component={Dashboard} />

        {/* FISCAL */}

        <Route exact path="/fiscal" component={Fiscal} />

        <Route path="/fiscal/detalhes/:id" component={DetalhesFiscal} />

        <Route path="/fiscal/editar/1/:id" component={FiscalCadastro1} />

        <Route path="/fiscal/editar/2/:id" component={FiscalCadastro2} />

        <Route path="/fiscal/editar/3/:id" component={FiscalCadastro3} />
{/* 
        <Route path="/fiscal/cadastro/1" component={FiscalCadastro1} />

        <Route path="/fiscal/cadastro/2" component={FiscalCadastro2} />

        <Route path="/fiscal/cadastro/3" component={FiscalCadastro3} /> */}

        <Route path="/fiscal/cadastro/:id" component={CadastroFiscal} />

        {/* MOTORISTA */}

         <Route exact path="/motorista" component={Motorista} /> 

        {/* <Route path="/motorista/detalhes/:id" component={} /> */}

        <Route path="/motorista/editar/1/:id" component={CadastroMotorista1} />

        <Route path="/motorista/editar/2/:id" component={CadastroMotorista2} />

        <Route path="/motorista/editar/3/:id" component={CadastroMotorista3} />

        <Route path="/motorista/cadastro/1" component={CadastroMotorista1} />

        <Route path="/motorista/cadastro/2" component={CadastroMotorista2} />

        <Route path="/motorista/cadastro/3" component={CadastroMotorista3} />

        {/* GERENTE */}

        {/* <Route exact path="/gerente" component={} /> */}

        {/* <Route path="/gerente/detalhes/:id" component={} /> */}

        <Route path="/gerente/editar/1/:id" component={CadastroGerente1} />

        <Route path="/gerente/editar/2/:id" component={CadastroGerente2} />

        <Route path="/gerente/editar/3/:id" component={CadastroGerente3} />

        <Route path="/gerente/cadastro/1" component={CadastroGerente1} />

        <Route path="/gerente/cadastro/2" component={CadastroGerente2} />

        <Route path="/gerente/cadastro/3" component={CadastroGerente3} />

        {/* ADMIN */}

        {/* <Route exact path="/admin" component={} /> */}

        {/* <Route path="/admin/detalhes/:id" component={} /> */}

        {/* <Route path="/admin/editar/:id" component={} /> */}

        <Route path="/admin/cadastro" component={CadastroAdmin} />

        {/* LINHA */}

        {/* <Route exact path="/linha" component={} /> */}

        {/* <Route path="/linha/detalhes/:id" component={} /> */}

        {/* <Route path="/linha/editar/:id" component={} /> */}

        <Route path="/linha/cadastro" component={CadastroParadaLinha} />
        <Route path="/linha" component={Linha} />

        {/* ONIBUS */}

        {/* <Route exact path="/onibus" component={} /> */}

        {/* <Route path="/onibus/detalhes/:id" component={} /> */}

        {/* <Route path="/onibus/editar/:id" component={} /> */}

        <Route path="/onibus/cadastro" component={CadastroOnibus} />
      </div>
    </Router>
  );
};

export default App;
