import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import PrivateRoute from './services/privateRoute';

import Fiscal from "./pages/VisualizaFiscal";
import Linha from "./pages/VisualizaLinha";
import Motorista from "./pages/VisualizaMotorista";
import Dashboard from "./pages/Dashboard";
import DetalhesFiscal from "./pages/DetalhesFiscal";
import DetalhesOnibus from "./pages/DetalhesOnibus";
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
import VisualizaOnibus from "./pages/VisualizaOnibus";
import Perfil from "./pages/PerfilGerente";
import Alocacao from './pages/Alocacao';
import Login from "./pages/Login";
import MenuLateral from "./components/MenuLateral";

import './App.css';

const Routes = () => (
  <BrowserRouter>
    <MenuLateral />
    <Switch>
      <Route exact path="/" component={Login} />
      <div className="conteudo">
        <PrivateRoute exact path="/dashboard" component={Dashboard} />
        <PrivateRoute exact path="/alocacao" component={Alocacao} />
        <PrivateRoute exact path="/parada" component={VisualizaParada} />

        {/* FISCAL */}
        <PrivateRoute exact path="/fiscal" component={Fiscal} />
        <PrivateRoute path="/fiscal/detalhes/:id" component={DetalhesFiscal} />
        <PrivateRoute path="/fiscal/editar/:id" component={CadastroFiscal} />
        <PrivateRoute path="/fiscal/cadastro" component={CadastroFiscal} />

        {/* MOTORISTA */}
        <PrivateRoute exact path="/motorista" component={Motorista} />
        <PrivateRoute
          path="/motorista/detalhes/:id"
          component={DetalhesMotorista}
        />
        <PrivateRoute
          path="/motorista/editar/:id"
          component={CadastroMotorista}
        />
        <PrivateRoute
          path="/motorista/cadastro"
          component={CadastroMotorista}
        />

        {/* GERENTE */}
        <PrivateRoute exact path="/gerente" component={ConsultaGerente} />
        <PrivateRoute
          path="/gerente/detalhes/:id"
          component={DetalhesGerente}
        />
        <PrivateRoute path="/gerente/editar/:id" component={CadastroGerente} />
        <PrivateRoute path="/gerente/cadastro" component={CadastroGerente} />

        {/* administrador */}
        <PrivateRoute exact path="/administrador" component={ConsultaAdmin} />
        <PrivateRoute
          path="/administrador/editar/:id"
          component={CadastroAdmin}
        />
        <PrivateRoute
          path="/administrador/cadastro"
          component={CadastroAdmin}
        />

        {/* LINHA */}
        <PrivateRoute path="/linha/detalhes/:id" component={DetalhesLinha} />
        <PrivateRoute
          path="/linha/editar/:id"
          component={CadastroParadaLinha}
        />
        <PrivateRoute path="/linha/cadastro" component={CadastroParadaLinha} />
        <PrivateRoute exact path="/linha" component={Linha} />

        {/* ONIBUS */}
        <PrivateRoute exact path="/onibus" component={VisualizaOnibus} />
        <PrivateRoute path="/onibus/detalhes/:id" component={DetalhesOnibus} />
        <PrivateRoute path="/onibus/editar/:id" component={CadastroOnibus} />
        <PrivateRoute path="/onibus/cadastro" component={CadastroOnibus} />

        {/* Perfil */}
        <PrivateRoute path="/perfil" component={Perfil} />
      </div>
    </Switch>
  </BrowserRouter>
);

export default Routes;