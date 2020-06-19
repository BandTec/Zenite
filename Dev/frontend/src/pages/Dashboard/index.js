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
  const [qtdCarrosCirculando, setQtdCarrosCirculando] = useState();
  const [onibusCirculando, setOnibusCirculando] = useState();
  const [viagemPeriodoLinha, setViagemPeriodoLinha] = useState();
  const [tempoMedioViagemDiaDaSemana, setTempoMedioViagemDiaDaSemana] = useState();
  const [motoristasAlocados, setMotoriostasAlocados] = useState();
  const [fiscalResponsavel, setFiscalResponsavel] = useState();
  const [horaAtual, setHoraAtual] = useState("");
  const [linhas, setLinhas] = useState([]);

  const linhasComMaiorAtraso = {
    labels: ["4051-10", "3033-10", "407M-10", "7245-10", "3766-10", "4001-10", "3754-10", "3686-10", "407T-10", "312N-10"],
    data: [49, 20, 25, 39, 25, 27, 43, 29, 25,58]
  }

  async function loadDados(){
    const response = await api.get("/api/dashboard");
    setDados(response.data);
    setHoraAtual(`${data.getHours()}:${data.getMinutes()}`)
  }

  async function loadDadosLinha(idLinha){
    const response = await api.get(`/api/dashboard/${idLinha}`);
    setDadosLinha(response.data);
    setHoraAtual(`${data.getHours()}:${data.getMinutes()}`)
  }

  // setInterval(() =>{
  //   loadDados();
  //   loadDadosLinha(); 
  // }, 300000)
  
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
      aux = ["Linha", "Fiscal", "Circulando", "Motoristas", "Viagem"]
      const auxLinhas = []
      for(let elem of dados.dadosLinha){
        const fiscalCompleto = elem["fiscalIda"].split(" ")
        const fiscal = fiscalCompleto[0]+" "+fiscalCompleto[fiscalCompleto.length-1]
        auxLinhas.push({idLinha: elem["idLinha"], numero: elem["numeroLinha"]});
        auxDado.push([
          elem["numeroLinha"], 
          fiscal,
          elem["qtdCarrosCirculando"], 
          elem["qtdMotorista"],
          elem["mediaViagem"]
        ])
      }
      setTbDadosLinha({header: aux, body: auxDado})
      setLinhas(auxLinhas)
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
    if(dadosLinha.onibusCirculando){
      for(let elem of dadosLinha.onibusCirculando){
        aux.push(elem['periodo'])
        auxDado.push(elem['qtdCarros'])
      }
      setOnibusCirculando({labels: aux, data: auxDado})
      aux = []
      auxDado = []
    }
    if(dadosLinha.viagemPeriodoLinha){
      for(let elem of dadosLinha.viagemPeriodoLinha){
        aux.push(elem['periodo'])
        auxDado.push(elem['tempoViagemPeriodo'])
      }
      setViagemPeriodoLinha({labels: aux, data: auxDado})
      aux = []
      auxDado = []
    }
    if(dadosLinha.tempoMedioViagemDiaDaSemana){
      for(let elem of dadosLinha.tempoMedioViagemDiaDaSemana){
        aux.push(elem['diaSemana'])
        auxDado.push(elem['tempoViagemPeriodo'])
      }
      setTempoMedioViagemDiaDaSemana({labels: aux, data: auxDado})
      aux = []
      auxDado = []
    }
    if(dadosLinha.onibusAlocados) setQtdCarrosCirculando(dadosLinha.onibusAlocados);
    if(dadosLinha.motoristasAlocados) setMotoriostasAlocados(dadosLinha.motoristasAlocados);
    if(dadosLinha.fiscalResponsavel) setFiscalResponsavel(dadosLinha.fiscalResponsavel);

  },[dadosLinha])

  const handlePage = newPage => setPage(newPage)

  return (
    <Container>
      <Tela>
        <Row>
          <Titulo
            textoMenor={`Atualizado em ${horaAtual}`}
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
                dados={linhasComMaiorAtraso}
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"4 / 5"} 
              cor="claro"
            >
              <Texto 
                titulo="Atraso médio (por viagem)" 
                valor="15 min"
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
              >
                <select onChange={e => loadDadosLinha(e.target.value)}>
                  <option value="0">Escolha</option>
                  {
                    linhas.map(linha => <option value={linha.idLinha}>{linha.numero}</option>)
                  }
                </select>
              </Texto>
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
                valor={qtdCarrosCirculando}
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
                valor={motoristasAlocados} 
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
                valor={fiscalResponsavel}
              />
            </Card>
            <Card 
              column={"3 / 5"} 
              row={"3 / 4"} 
              cor="suave"
            >
              <Bar 
                titulo="Tempo médio de vigem (por dia da semana)"
                dados={tempoMedioViagemDiaDaSemana}
              />
            </Card>
            <Card 
              column={"1 / 3"} 
              row={"4 / 6"} 
              cor="suave"
            >
              <Line 
                titulo="Quantidade de ônibus circulando (por período)"
                dados={onibusCirculando}
              />
            </Card>
            <Card 
              column={"3 / 5"} 
              row={"4 / 6"} 
              cor="claro"
            >
              <Bar 
                titulo="Tempo de viagem (por período)"
                dados={viagemPeriodoLinha}
              />
            </Card>
          </CorpoLinha>
        }
      </Tela>
    </Container>
  );
}
