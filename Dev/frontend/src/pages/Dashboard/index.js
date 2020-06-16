import React, { useEffect, useState } from "react";

import { Container, Tela, Corpo, Cabecalho, Row, Perfil, CorpoLinha } from './styles';
import Card from '../../components/Card';
import Titulo from '../../components/Titulo';
import Filtro from '../../components/Filtro';
import { Bar, Pie, Line, Table, Radar } from '../../components/lib';
import api from '../../services/api';
import Texto from './TiposCard/Texto';
import Quadrado from './TiposCard/Quadrado';

var data = new Date()

export default function Dashboard() {

  const [nome, setNome] = useState(0);
  const [page, setPage] = useState("");
  const [dados, setDados] = useState({});
  const [dadosLinha, setDadosLinha] = useState({});
  const [dadosOperandoParado, setDadosOperandoParado] = useState({});
  const [tempoMedioViagemPeriodo, setTempoMedioViagemPeriodo] = useState({});
  const [viagemMotorista, setViagemMotorista] = useState({});
  const [tbDadosLinha, setTbDadosLinha] = useState();

  async function loadDados(){
    const response = await api.get("/api/dashboard");
    setDados(response.data);
  }

  async function loadDadosLinha(){
    const response = await api.get("/api/dashboard/2");
    setDadosLinha(response.data);
  }
  
  useEffect(()=>{
    setNome(localStorage.getItem("nome"));
    setPage("Geral")
  },[])
  useEffect( () => {
    loadDados()
    loadDadosLinha()
  }, [page]);  

  useEffect(() => {
    let aux = [];
    let auxDado = [];
    if(dados.operandoParado){
      for (const [key, value] of Object.entries(dados.operandoParado)) {
        aux.push(key)
        auxDado.push(value)
      }
      setDadosOperandoParado({labels: aux, data: auxDado})
      aux = []
      auxDado = []
    }
    if(dados.tempoMedioViagemPeriodo){
      for(let elem of dados.tempoMedioViagemPeriodo){
          aux.push(elem['periodo'])
          auxDado.push(elem['tempoMedio'])
      }
      setTempoMedioViagemPeriodo({labels: aux, data: auxDado})
      aux = []
      auxDado = []
    }
    if(dados.dadosLinha){
      for (const [key, ] of Object.entries(dados.dadosLinha[0])) {
        aux.push(key)
      }
      for(let elem of dados.dadosLinha){
        let dadoTabela = []
        for (const [, value] of Object.entries(elem)) {
          dadoTabela.push(value)
        }
        auxDado.push(dadoTabela)
      }
      setTbDadosLinha({header: aux, body: auxDado})
      aux = []
      auxDado = []
    }
  },[dados])

  useEffect(() => {
    let aux = [], auxDado = [];

    if(dadosLinha.viagemMotorista){
      for(let elem of dadosLinha.viagemMotorista){
        aux.push(elem['nome'])
        auxDado.push(elem['mediaViagemPorMotorista'])
      }
      setViagemMotorista({labels: aux, data: auxDado})
      aux = []
      auxDado = []
    }
  },[dadosLinha])

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
        { page === 'Geral' && dados ?
          <Corpo>
            <Card 
              column={"1 / 3"} 
              row={"1 / 2"}  
              cor="claro"
            >
              <Quadrado 
                titulo="Tempo médio" 
                subTitulo="(ULTIMA 1 HORA)" 
                valor={dados.tempoMedioViagemHora} 
                cor="claro"
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"2 / 4"} 
              cor="suave"
            >
              <Bar 
                titulo="Linhas com maior atraso"
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"4 / 5"} 
              cor="claro"
            >
              <Texto 
                titulo="Atraso médio (por viagem)" 
                valor="50 min"
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"5 / 7"} 
              cor="claro"
            >
              <Line 
                titulo="Tempo médio de viagem (por período)"
                dados={tempoMedioViagemPeriodo}
              />
            </Card>
            <Card 
              column={"3 / 5"}
              row={"1 / 4"}
              cor="suave"
              >
                <Pie 
                  titulo="Ônibus operando x Ônibus parado"
                  dados={dadosOperandoParado}
                />
              </Card>
            <Card 
              column={"3 / 5"} 
              row={"4 / 5"} 
              cor="escuro"
            >
              <Texto 
                titulo="Quantidade de ônibus não alocados" 
                valor={dados.carrosNaoAlocados}
              />
            </Card>
            <Card 
              column={"3 / 5"} 
              row={"5 / 7"} 
              cor="claro"
            >
              <Table 
                titulo="Linha"
                dados={ tbDadosLinha }
              />
            </Card>
          </Corpo> 
          : <CorpoLinha>
            <Card 
              column={"1 / 3"} 
              row={"1 / 2"} 
              cor="escuro"
            >
              <Texto 
                titulo="Linha" 
                valor="4051-10"
              />
            </Card>
            <Card 
              column={"3 / 5"} 
              row={"1 / 3"} 
              cor="claro"
            >
              <Radar 
                titulo="Tempo médio de viagem (por motorista)"
                dados={viagemMotorista}
              />
            </Card>
            <Card 
              column={"1 / 2"} 
              row={"2 / 3"} 
              cor="suave"
            >
              <Quadrado 
                titulo="Ônibus alocados" 
                valor="30" 
                cor="suave"
              />
            </Card>
            <Card 
              column={"2 / 3"} 
              row={"2 / 3"} 
              cor="claro"
            >
              <Quadrado 
                titulo="Motoristas alocados"
                valor="15" 
                cor="claro"
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"3 / 4"} 
              cor="claro"
            >
              <Texto 
                titulo="Fiscal responsável" 
                valor="Claúdio Silva"
              />
            </Card>
            <Card 
              column={"3 / 5"} 
              row={"3 / 4"} 
              cor="suave"
            >
              <Bar 
                titulo="Tempo médio de vigem (por dia da semana)"
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"4 / 6"} 
              cor="suave"
            >
              <Line 
                titulo="Quantidade de ônibus circulando (por período)"
              />
            </Card>
            <Card 
              column={"3 / 5"} 
              row={"4 / 6"} 
              cor="claro"
            >
              <Bar 
                titulo="Tempo de viagem (por período)"
              />
            </Card>
          </CorpoLinha>
        }
      </Tela>
    </Container>
  );
}
