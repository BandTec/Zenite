
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
import CadastroMotorista from "./pages/CadastroMotorista";
import CadastroGerente from "./pages/CadastroGerente";
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

        <Route path="/fiscal/editar/:id" component={CadastroFiscal} />

        <Route path="/fiscal/cadastro/:id" component={CadastroFiscal} />

        {/* MOTORISTA */}

         <Route exact path="/motorista" component={Motorista} /> 

        {/* <Route path="/motorista/detalhes/:id" component={DetalhesMotorista} />  */}

        <Route exact path="motorista/editar/:id" component={CadastroMotorista}/>

        <Route exact path="motorista/cadastro/:id" component={CadastroMotorista}/>

        
        {/* GERENTE */}

        {/* <Route exact path="/gerente" component={} /> */}

        {/* <Route path="/gerente/detalhes/:id" component={} /> */}

        <Route path="/gerente/editar/:id" component={CadastroGerente} />

        <Route path="/gerente/cadastro" component={CadastroGerente} />

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
