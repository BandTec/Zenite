
import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";

import './App.css';
import Login from "./pages/Login";
import Fiscal from "./pages/VisualizaFiscal";
import Linha from "./pages/VisualizaLinha";
import Motorista from "./pages/VisualizaMotorista";
import Dashboard from "./pages/Dashboard";
import DetalhesFiscal from "./pages/DetalhesFiscal";
import DetalhesMotorista from "./pages/DetalhesMotorista"
import DetalhesLinha from "./pages/DetalhesLinhas";
import DetalhesGerente from "./pages/DetalhesGerente";
import CadastroFiscal from "./pages/CadastroFiscal";
import CadastroMotorista from "./pages/CadastroMotorista";
import CadastroGerente from "./pages/CadastroGerente";
import CadastroAdmin from "./pages/CadastroAdmin";
import ConsultaAdmin from "./pages/VisualizaAdmin";
import VisualizaParada from "./pages/VisualizaParadas";
import ConsultaGerente from "./pages/VisualizaGerente";
import CadastroParadaLinha from "./pages/CadastroParadaLinha";
import CadastroOnibus from "./pages/CadastroOnibus";
import Perfil from "./pages/PerfilGerente";

import MenuLateral from "./components/MenuLateral";
import Alocacao from './pages/Alocacao';

const App = () => {
  return (
    <Router>
      <MenuLateral />

      <Route path="/login" component={Login} />

      <div className="conteudo">
        <Route exact path="/dashboard" component={Dashboard} />

        <Route exact path="/alocacao" component={Alocacao} />

        <Route exact path="/parada" component={VisualizaParada} />

        {/* FISCAL */}

        <Route exact path="/fiscal" component={Fiscal} />

        <Route path="/fiscal/detalhes/:id" component={DetalhesFiscal} />

        <Route path="/fiscal/editar/:id" component={CadastroFiscal} />

        <Route path="/fiscal/cadastro" component={CadastroFiscal} />

        {/* MOTORISTA */}

        <Route exact path="/motorista" component={Motorista} />

        <Route path="/motorista/detalhes/:id" component={DetalhesMotorista} />

        <Route path="/motorista/editar/:id" component={CadastroMotorista} />

        <Route path="/motorista/cadastro" component={CadastroMotorista} />

        {/* GERENTE */}

        <Route exact path="/gerente" component={ConsultaGerente} />

        <Route path="/gerente/detalhes/:id" component={DetalhesGerente} />

        <Route path="/gerente/editar/:id" component={CadastroGerente} />

        <Route path="/gerente/cadastro" component={CadastroGerente} />

        {/* administrador */}

        <Route exact path="/administrador" component={ConsultaAdmin} />

        <Route path="/administrador/editar/:id" component={CadastroAdmin} />

        <Route path="/administrador/cadastro" component={CadastroAdmin} />

        {/* LINHA */}

        <Route path="/linha/detalhes/:id" component={DetalhesLinha} />

        <Route path="/linha/editar/:id" component={CadastroParadaLinha} />

        <Route path="/linha/cadastro" component={CadastroParadaLinha} />

        <Route exact path="/linha" component={Linha} />

        {/* ONIBUS */}

        {/* <Route exact path="/onibus" component={} /> */}

        {/* <Route path="/onibus/detalhes/:id" component={} /> */}

        {/* <Route path="/onibus/editar/:id" component={} /> */}

        <Route path="/onibus/cadastro" component={CadastroOnibus} />

        {/* Perfil */}

        <Route path="/perfil" component={Perfil} />
      </div>
    </Router>
  );
};

export default App;
