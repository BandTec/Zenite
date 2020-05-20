import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import api from "../../services/api";
import Swal from 'sweetalert2';

import {
  Container,
  CaixaHorizontal,
  CorpoPagina,
  FormContainer,
  Titulo,
  Subtitulo,
  Caixa
} from "./styles";
import BotaoForm from "../../components/BotaoForm";
import Botao from "../../components/Botao";

import InputComRotulo from "../../components/InputComRotulo";
import ComboBoxComRotulo from "../../components/ComboBoxComRotulo";

export default function CadastroLinha(props) {
   const caminho = props.match.path;
   const id = props.match.params.id;
   const isEdicao = caminho.includes("editar");
   const tipoPagina = isEdicao ? "Edição" : "Cadastro";
   
  const [linhaNumero, setLinhaNumero] = useState("");
  const [novaParadaIda, setNovaParadaIda] = useState(false);
  const [novaParadaVolta, setNovaParadaVolta] = useState(false);

  const [paradaIda, setParadaIda] = useState("");
  const [paradaVolta, setParadaVolta] = useState("");
  const [idParadaIda, setIdParadaIda] = useState("");
  const [idParadaVolta, setIdParadaVolta] = useState("");
  const [paradas, setParadas] = useState([]);

  useEffect(() => {
      async function consultaDadosIniciais() {
        const token = localStorage.getItem("token");

        const response = await api.get("/api/pontofinal", {
          headers: { Authorization: token },
        });
        let temp = [{ value: "", texto: "Escolha uma parada" }];
        const dados = response.data;
        dados.forEach((item) => {
          temp.push({ value: item.id, texto: item.nome });
        });
        setParadas(temp);
      }

      consultaDadosIniciais();
  }, []);

  useEffect(()=> {

    async function consultarEdicao() {
      const token = localStorage.getItem("token");
      try {
        const response = await api.get(`/api/linha/${id}`, {
          headers: { Authorization: token },
        });

        const dados = response.data;

        setParadaVolta(dados.pontoVolta.nome);
        setParadaIda(dados.pontoIda.nome);

        setIdParadaIda(dados.pontoIda.id);
        setIdParadaVolta(dados.pontoVolta.id);

        setLinhaNumero(dados.numero);
      } catch(e) {
        alert("Ocorreu um erro. Tente de novo");
      }
    }
      id && consultarEdicao();
  }, [id])

  const cadastrar = async () => {
    let ida = novaParadaIda ? {nome: paradaIda} : { id: idParadaIda} ;
    let volta = novaParadaVolta ? {nome: paradaVolta} : { id: idParadaVolta};
    let dados = {
      numero: linhaNumero,
      pontoIda: ida,
      pontoVolta: volta,
    };
    try {
      const token = await localStorage.getItem("token");
      const response = await api.post("/api/linha", dados, {
        headers: { Authorization: token },
      });
      
      if (response.status === 201) {
        props.history.push("/linha");
        Swal.fire({
          position: 'flex-end',
          icon: 'success',
          title: 'Cadastrado com Sucesso',
          showConfirmButton: false,
          timer: 2000
        });
      } else {
        alert("Ocorreu um erro. Tente de novo");
      }
   }catch(e) {
    Swal.fire({
      title:'Tente novamente',
      text:'Ocorreu um imprevisto, por gentileza tente novamente.',
      icon:'error',
      showConfirmButton: false,
       });
    }
  };

  const editar = async () => {
    let ida = novaParadaIda ? { nome: paradaIda } : { id: idParadaIda };
    let volta = novaParadaVolta ? { nome: paradaVolta } : { id: idParadaVolta };
    let dados = {
      id: id,
      numero: linhaNumero,
      pontoIda: ida,
      pontoVolta: volta,
    };

    Swal.fire({
      title: 'Aviso',
      text: 'Deseja realmente excluir este dado? ',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText:'Sim, desejo',
      cancelButtonText: 'Não',
    })

    try {
      const token = await localStorage.getItem("token");
      const response = await api.put("/api/linha", dados, {
        headers: { Authorization: token },
      });
      console.log(response);
      if (response.status === 200) {
        props.history.push("/linha");
        Swal.fire({
          position: 'flex-end',
          icon: 'success',
          title: 'Alterado com Sucesso',
          showConfirmButton: false,
        });    
        window.location.reload();
      } else {
        alert("Ocorreu um erro. Tente de novo");
      }
    } catch (e) {
      Swal.fire({
        title:'Tente novamente',
        text:'Ocorreu um imprevisto, por gentileza tente novamente.',
        icon:'error',
        showConfirmButton: false,
         });
    }
  };

  const concluir = () => {
    isEdicao ? editar() : cadastrar();
  }

  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/linha">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

          <Caixa>
            <Subtitulo>{tipoPagina} DA LINHA</Subtitulo>
            <Titulo>Dados de Acesso</Titulo>

            <InputComRotulo
              texto="Número da Linha"
              maxLength="7"
              name="numeroLinha"
              type="text"
              value={linhaNumero}
              onChange={(e) => setLinhaNumero(e.target.value)}
              required
            />

            <CaixaHorizontal>
              <Caixa>
                {novaParadaIda && (
                  <InputComRotulo
                    texto="Parada Ida"
                    maxLength="80"
                    name="paradaIda"
                    type="text"
                    value={paradaIda}
                    onChange={(e) => setParadaIda(e.target.value)}
                    required
                    pequeno={true}
                  />
                )}

                {!novaParadaIda && (
                  <ComboBoxComRotulo
                    texto="Parada Ida"
                    conteudoCombo={paradas}
                    pequeno={true}
                    stateSelecionado={idParadaIda}
                    onchange={(e) => setIdParadaIda(e.target.value)}
                  />
                )}

                <Botao
                  descricao="Nova parada Ida"
                  onClick={() => setNovaParadaIda(!novaParadaIda)}
                />
              </Caixa>

              <Caixa>
                {novaParadaVolta && (
                  <InputComRotulo
                    texto="Parada Volta"
                    maxLength="7"
                    name="numeroLinha"
                    type="text"
                    required
                    value={paradaVolta}
                    onChange={(e) => setParadaVolta(e.target.value)}
                    pequeno={true}
                  />
                )}

                {!novaParadaVolta && (
                  <ComboBoxComRotulo
                    texto="Parada Volta"
                    conteudoCombo={paradas}
                    pequeno={true}
                    stateSelecionado={idParadaVolta}
                    onchange={(e) => setIdParadaVolta(e.target.value)}
                  />
                )}

                <Botao
                  descricao="Nova parada volta"
                  onClick={() => setNovaParadaVolta(!novaParadaVolta)}
                />
              </Caixa>
            </CaixaHorizontal>
          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true} criarJson={concluir} />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
