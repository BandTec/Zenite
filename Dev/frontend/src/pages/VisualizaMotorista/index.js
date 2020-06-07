import React, { useEffect, useState } from 'react';
import api from '../../services/api';

import { Container, Row} from './styles';
import Tabela from "./../../components/Tabela2";
import Paginacao from '../../components/Paginacao';
import Loader from "./../../components/Loader";
import CabecalhoConsulta from "../../components/CabecalhoConsulta";

export default function ConsultaMotorista() {
   
  const [corpo, setCorpo] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [total, setTotal] = useState(0);
  const [totalItens, setTotalItens] = useState(0);
  const [atual, setAtual] = useState(0);

  useEffect(() => {
    async function dadosCorpos() {
      //Essa linha de baixo pega o token de autenticação do localStorage
      const token = localStorage.getItem("token");

      //Essa de baixo, faz a chamada GET pra rota /api/linha, passando o token como cabeçalho e passa pra
      //uma variavel response
      const response = await api.get(`/api/motorista?pagina=${pagina}`, {
        headers: { Authorization: token },
      });

      //aqui pego do response.data que é onde tá os dados da linha e passo pra uma variavel tbm
      let dados = response.data;
      setAtual(dados.paginaAtual);
      setTotal(dados.totalPaginas);
      setTotalItens(dados.totalItens);
      let temp = [];
      dados.lista.forEach((item) => {
        temp.push(
          criaDados(item.id, item.nome, item.numeroTelefone, item.cpf, item.cnh, item.carro ? item.carro.numero : "Não")
        );
      });
      setCorpo(temp);
    }

    dadosCorpos();
  }, [pagina]);

  function criaDados(id, nome, telefone, cpf, cnh, alocado_a_onibus){
    return { id, nome, telefone, cpf, cnh, alocado_a_onibus };
  }

  return corpo.length <= 0 ? (
    <Loader />
  ) : (
    <Container>
      <CabecalhoConsulta
        botaoTitulo="Novo Motorista"
        titulo="Motorista"
        url="motorista"
        totalItens={totalItens}
      />

      <Row>
        <Tabela tipo="motorista" dados={corpo} />
      </Row>

      <Row>
        <Paginacao
          pgAtual={atual}
          totalPg={total}
          voltar={() => setPagina(pagina - 1)}
          proximo={() => setPagina(pagina + 1)}
          totalItens={totalItens}
        />
      </Row>
    </Container>
  );
}
