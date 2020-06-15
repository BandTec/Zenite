import React, { useEffect, useState } from "react";

import { Container, Tela, Corpo, Cabecalho, Row, Perfil, CorpoLinha } from './styles';
import Card from '../../components/Card';
import Titulo from '../../components/Titulo';
import Filtro from '../../components/Filtro';
import { Bar, Pie, Line, Table, Radar } from '../../components/lib';

import Texto from './TiposCard/Texto';
import Quadrado from './TiposCard/Quadrado';

var data = new Date()

export default function Dashboard() {

  const [nome, setNome] = useState(0);
  const [page, setPage] = useState("");
  useEffect(() => {
    setNome(localStorage.getItem("nome"));
    setPage("Geral")
  }, []);

  const handlePage = newPage => setPage(newPage)

  return (
    <Container>
      <Tela>
        <Row>
          <Titulo
            textoMenor={`Atualizado em ${data.getHours()}:${data.getMinutes()}`}
            textoMaior="Dashboard"
          />
          <Perfil>Olá, {nome}</Perfil>
        </Row>
        <Cabecalho>
          <Filtro selected={page==='Geral'} titulo="Geral" handlePage={handlePage}/>
          <Filtro selected={page==='Linha'} titulo="Linha" handlePage={handlePage}/>
        </Cabecalho>
        { page === 'Geral' ?
          <Corpo>
            <Card column={"1 / 3"} row={"1 / 2"} cor="escuro"><Texto titulo="QUANTIDADE MÉDIA DE PASSAGEIROS POR LINHA" valor="1500"/></Card>
            <Card column={"1 / 3"} row={"2 / 4"} cor="suave"><Bar titulo="LINHAS COM MAIOR ATRASO"/></Card>
            <Card column={"1 / 3"} row={"4 / 5"} cor="claro"><Texto titulo="Atraso médio por viagem" valor="50 min"/></Card>
            <Card column={"1 / 3"} row={"5 / 7"} cor="claro"><Line titulo="Tempo médio de viagem por periodo "/></Card>
            <Card column={"1 / 3"} row={"7 / 10"} cor="escuro"><Bar/></Card>
            <Card column={"3 / 5"} row={"1 / 4"} cor="suave"><Pie titulo="Ônibus operando x Ônibus parado"/></Card>
            <Card column={"3 / 5"} row={"4 / 5"} cor="escuro"><Texto titulo="Quantidade de ônibus não alocados" valor="30"/></Card>
            <Card column={"3 / 5"} row={"5 / 7"} cor="claro"><Table titulo="LINHA"/></Card>
            <Card column={"3 / 5"} row={"7 / 9"} cor="suave"><Bar titulo = "QUANTIDADE MÉDIA DE PASSAGEIROS"/></Card>
            <Card column={"3 / 4"} row={"9 / 10"} cor="suave"><Quadrado titulo="TEMPO MÉDIO" subTitulo="(ULTIMA 1 HORA)" valor="50 MIN" cor="suave"/></Card>
            <Card column={"4 / 5"} row={"9 / 10"} cor="claro"><Quadrado titulo="TEMPO MÉDIO" subTitulo="(ULTIMA 1 HORA)" valor="50 MIN" cor="claro"/></Card>
          </Corpo> 
          : <CorpoLinha>
            <Card column={"1 / 3"} row={"1 / 2"} cor="escuro"><Texto titulo="Linha" valor="4051-10"/></Card>
            <Card column={"3 / 5"} row={"1 / 3"} cor="claro"><Radar titulo="Atraso médio por motorista"/></Card>
            <Card column={"1 / 2"} row={"2 / 3"} cor="suave"><Quadrado titulo="Ônibus alocados" valor="30" cor="suave"/></Card>
            <Card column={"2 / 3"} row={"2 / 3"} cor="claro"><Quadrado titulo="Motoristas alocados" valor="15" cor="claro"/></Card>
            <Card column={"1 / 3"} row={"3 / 4"} cor="claro"><Texto titulo="FISCAL RESPONSÁVEL" valor="Claúdio Silva"/></Card>
            <Card column={"3 / 5"} row={"3 / 4"} cor="suave"><Bar titulo="TEMPO MÉDIO DE VIAGEM NA SEMANA"/></Card>
            <Card column={"1 / 5"} row={"4 / 7"} cor="suave"><Line titulo=""/></Card>
            <Card column={"1 / 3"} row={"7 / 9"} cor="claro"><Bar titulo="TEMPO DE VIAGEM"/></Card>
            <Card column={"3 / 5"} row={"7 / 9"} cor="suave"></Card>
          </CorpoLinha>
        }
      </Tela>
    </Container>
  );
}
