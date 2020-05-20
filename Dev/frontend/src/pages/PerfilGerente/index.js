import React, { useState, useEffect } from "react";
import api from "../../services/api";

import {
  Container,
  CorpoPagina,
  FormContainer,
  Subtitulo,
  Caixa,
  CaixaHorizontal
} from "./styles";

import Loader from "./../../components/Loader";
import BotaoForm from "./../../components/BotaoForm";
import Acesso from './Acesso';
import Endereco from "./DadosEndereco";
import Pessoais from './DadosPessoais';
import StatusPage from "./../../components/StatusPage";

export default function Perfil(props) {
  const [dados, setDados] = useState({});
  const [pagina, setPagina] = useState(0);
  const [validacaoSenha, setValidacaoSenha] = useState(false);
  // const [idUsuario, setIdUsuario] = useState(0);
  const [rota, setRota] = useState("");

  const mudarPagina = (isProximo) => {
    isProximo ? setPagina(pagina + 1) : setPagina(pagina - 1);
  };

  const adicionarDados = (novoDado) => {
    setDados({ ...dados, ...novoDado });
  };

 function tipoUsuario(dados) {
  //  setIdUsuario(dados.id);
   const nivel = dados.conta.nivel.id;
   switch (nivel) {
     case 1:
       setRota("administrador");
       break;
     case 2:
       setRota("gerente");
       break;
     case 3:
       setRota("fiscal");
       break;
     case 4:
       setRota("motorista");
       break;
       default:
         setRota("");
         break;
   }
 }

  useEffect(() => {
    async function consultarEdicao() {
      const token = localStorage.getItem("token");

      const response = await api.get(`/logado`, {
        headers: { Authorization: token },
      });
      
      
      let dadosConsulta = response.data;
      setDados({...dadosConsulta});
      tipoUsuario(dadosConsulta);
      setPagina(1);
    }

    consultarEdicao();
  }, []);

  const editar = async () => {
    const token = await localStorage.getItem("token");
    try {
      if (validacaoSenha){
           const response = await api.put(`/api/${rota}/${idUsuario}`, dados, {
             headers: { Authorization: token },
           });
      
        if (response.status === 204) {
          props.history.push("/login");
        } else {
          alert("Ocorreu um erro. Tente de novo");
        }
      }else {
          alert("Senha diferente, tente novamente.");
      }

    }catch(e) {
      alert("Ocorreu um erro. Tente de novo.");
    }
    
  };


  return (
    <Container>
      {Object.keys(dados).length === 0 ? (
        <Loader />
      ) : (
        <CorpoPagina>
          <CaixaHorizontal center={true}>
            <StatusPage
              ativo={pagina === 1}
              texto="Dados Pessoais"
              temProximoPasso={true}
            />

            <StatusPage
              ativo={pagina === 2}
              texto="Endereço"
              temProximoPasso={true}
            />

            <StatusPage
              ativo={pagina >= 3}
              texto="Dados de Acesso"
              temProximoPasso={false}
            />
          </CaixaHorizontal>

          <FormContainer>
            <BotaoForm
              texto="VOLTAR"
              ladoDireito={false}
              mudarPagina={mudarPagina}
            />

            <Caixa>
              <Subtitulo>Editar Perfil</Subtitulo>
              {pagina === 1 && (
                <Pessoais adicionarDados={adicionarDados} dados={dados} />
              )}

              {pagina === 2 && (
                <Endereco adicionarDados={adicionarDados} dados={dados} />
              )}

              {pagina >= 3 && (
                <Acesso
                  adicionarDados={adicionarDados}
                  dados={dados}
                  validarSenha={setValidacaoSenha}
                />
              )}
            </Caixa>

            <BotaoForm
              texto={pagina >= 3 ? "Finalizar" : "Próximo"}
              mudarPagina={mudarPagina}
              concluir={pagina >= 3}
              criarJson={pagina >= 3 ? editar : () => {}}
            />
          </FormContainer>
        </CorpoPagina>
      )}
    </Container>
  );
}
