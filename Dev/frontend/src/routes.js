import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import PrivateRoute from "./services/privateRoute";
// import yup from "./yupConfig";
import Fiscal from "./pages/VisualizaFiscal";
import Linha from "./pages/VisualizaLinha";
import Motorista from "./pages/VisualizaMotorista";
import Dashboard from "./pages/Dashboard";
import DetalhesFiscal from "./pages/DetalhesFiscal";
import DetalhesOnibus from "./pages/DetalhesOnibus";
import DetalhesMotorista from "./pages/DetalhesMotorista";
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
import VisualizaViagens from "./pages/VisualizaViagens";
import VisualizaCronograma from "./pages/VisualizaCronograma";
import VisualizaHorarios from "./pages/VisualizaHorarios";
import CadastroCronograma from "./pages/CadastroCronograma";
import NotFound from "./pages/NotFound";

import Perfil from "./pages/PerfilGerente";
import Alocacao from "./pages/Alocacao";
import Login from "./pages/Login";
import MenuLateral from "./components/MenuLateral";

import "./App.css";

const Routes = () => (
  <BrowserRouter>
    <MenuLateral />
    <Switch>
      <Route exact path="/" component={Login} />
      <div className="conteudo">
        <Switch>
          <PrivateRoute exact path="/dashboard" component={Dashboard} />
          <PrivateRoute exact path="/alocacao" component={Alocacao} />
          <PrivateRoute exact path="/parada" component={VisualizaParada} />

          {/* FISCAL */}
          <PrivateRoute exact path="/fiscal" component={Fiscal} />
          <PrivateRoute
            exact
            path="/fiscal/detalhes/:id"
            component={DetalhesFiscal}
          />
          <PrivateRoute
            exact
            path="/fiscal/editar/:id"
            component={CadastroFiscal}
          />
          <PrivateRoute path="/fiscal/cadastro" component={CadastroFiscal} />

          {/* MOTORISTA */}
          <PrivateRoute exact path="/motorista" component={Motorista} />
          <PrivateRoute
            exact
            path="/motorista/detalhes/:id"
            component={DetalhesMotorista}
          />
          <PrivateRoute
            exact
            path="/motorista/editar/:id"
            component={CadastroMotorista}
          />
          <PrivateRoute
            exact
            path="/motorista/cadastro"
            component={CadastroMotorista}
          />

          {/* GERENTE */}
          <PrivateRoute exact path="/gerente" component={ConsultaGerente} />
          <PrivateRoute
            exact
            path="/gerente/detalhes/:id"
            component={DetalhesGerente}
          />
          <PrivateRoute
            exact
            path="/gerente/editar/:id"
            component={CadastroGerente}
          />
          <PrivateRoute
            exact
            path="/gerente/cadastro"
            component={CadastroGerente}
          />

          {/* administrador */}
          <PrivateRoute exact path="/administrador" component={ConsultaAdmin} />
          <PrivateRoute
            exact
            path="/administrador/editar/:id"
            component={CadastroAdmin}
          />
          <PrivateRoute
            exact
            path="/administrador/cadastro"
            component={CadastroAdmin}
          />

          {/* LINHA */}
          <PrivateRoute
            exact
            path="/linha/detalhes/:id"
            component={DetalhesLinha}
          />
          <PrivateRoute
            exact
            path="/linha/editar/:id"
            component={CadastroParadaLinha}
          />
          <PrivateRoute
            exact
            path="/linha/cadastro"
            component={CadastroParadaLinha}
          />
          <PrivateRoute exact path="/linha" component={Linha} />

          {/* ONIBUS */}
          <PrivateRoute exact path="/onibus" component={VisualizaOnibus} />
          <PrivateRoute
            exact
            path="/onibus/detalhes/:id"
            component={DetalhesOnibus}
          />
          <PrivateRoute
            exact
            path="/onibus/editar/:id"
            component={CadastroOnibus}
          />
          <PrivateRoute
            exact
            path="/onibus/cadastro"
            component={CadastroOnibus}
          />

          <PrivateRoute
            exact
            path="/cronograma/detalhes/:id"
            component={VisualizaHorarios}
          />

          <PrivateRoute
            exact
            path="/cronograma/cadastro"
            component={CadastroCronograma}
          />

          <PrivateRoute
            exact
            path="/cronograma"
            component={VisualizaCronograma}
          />

          <PrivateRoute exact path="/viagem" component={VisualizaViagens} />

          {/* Perfil */}
          <PrivateRoute exact path="/perfil" component={Perfil} />

          <Route component={NotFound} />
        </Switch>
      </div>
    </Switch>
  </BrowserRouter>
);

export default Routes;
