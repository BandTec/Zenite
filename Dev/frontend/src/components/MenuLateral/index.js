import React from 'react';

import {
  Container,
  MainMenu
} from './styles';

import BotaoMenu from './BotaoMenu';

export default function MenuLateral({itemAtivo}) {

  return (
    <Container>
      <MainMenu>      
        <BotaoMenu 
          descricao="Início" 
          iconeNome="logo" alt="Logo do Software Zenite" 
          url={"/"}
          ativo={itemAtivo == "inicio" ? true : false}
        />


        <BotaoMenu 
          descricao="Fiscal" 
          iconeNome="fiscal"
          url={"/fiscal"}
          ativo={itemAtivo == "fiscal" ? true : false}
         />


        <BotaoMenu 
          descricao="Linha" 
          iconeNome="linha"
          url={"/linha"}
          ativo={itemAtivo == "linha" ? true : false}
         />


        <BotaoMenu 
          descricao="Motorista" 
          iconeNome="motorista"
          url={"/motorista"}
          ativo={itemAtivo == "motorista" ? true : false}
         />


        <BotaoMenu 
          descricao="Ônibus" 
          iconeNome="onibus"
          url={"/onibus"}
          ativo={itemAtivo == "onibus" ? true : false}
         />


        <BotaoMenu 
          descricao="Perfil" 
          url={"/perfil"}
          iconeNome="perfil"
          ativo={itemAtivo == "perfil" ? true : false}
         />

      </MainMenu>

      <BotaoMenu 
        descricao="Sair" 
        url={"/login"}
        iconeNome="logout" 
      />
    </Container>
  );
}
