import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import api from "../../services/api";
import Swal from 'sweetalert2';

import {
  Container,
  CorpoPagina,
  FormContainer,
  Titulo,
  Subtitulo,
  Caixa,
} from "./styles";
import BotaoForm from "./../../components/BotaoForm";
import InputComRotulo from "./../../components/InputComRotulo";

export default function CadastroAdmin(props) {
  const [valorSenha, setValorSenha] = useState("");
  const [valorConfirmarSenha, setValorConfirmarSenha] = useState("");
  const [validacaoSenha, setValidacaoSenha] = useState("");
  const [email, setEmail] = useState("");
  const [nome, setNome] = useState("");

   const caminho = props.match.path;
   const id = props.match.params.id;
   const isEdicao = caminho.includes("editar");
   const tipoPagina = isEdicao ? "Edição" : "Cadastro";

  const verificarSenha = () => {

    if(valorSenha.length >= 8){
      setValidacaoSenha(valorSenha === valorConfirmarSenha);
    }
  };

    useEffect(() => {
      async function consultarEdicao() {
        try {
          const token = localStorage.getItem("token");

          const response = await api.get(`/api/administrador/${id}`, {
            headers: { Authorization: token },
          });

          const dados = response.data;

          setNome(dados.nome);
          setEmail(dados.conta.email);
        } catch (e) {
          alert("Ocorreu um erro. Tente de novo.");
        }
      }

      consultarEdicao();
    }, [id]);

  const cadastrar = async () => {
    const dados = {
      nome: nome,
      conta: {
        senha: valorSenha,
        email: email,
        nivel: {
          id: 1,
        },
      },
    };

    verificarSenha();
    if (validacaoSenha) {
      try {
        const token = await localStorage.getItem("token");

        const response = await api.post("/api/administrador", dados, {
          headers: { Authorization: token },
        });

        if (response.status === 201) {
          props.history.push("/administrador");
        } 
      } catch (e) {
        alert("Ocorreu um erro. Tente de novo.");
      }

    } else {
      Swal.fire({
        position: 'flex-end',
        icon: 'warning',
        title: 'As senhas devem ser iguais',
        showConfirmButton: false,
        timer: 2000
      });
    }
  };
    const editar = async () => {
      const dados = {
        id,
        nome: nome,
        conta: {
          senha: valorSenha,
          email: email,
          nivel: {
            id: 1,
          },
        },
      };
       verificarSenha();
    if (validacaoSenha) {
      try {
        const token = await localStorage.getItem("token");
        const response = await api.put("/api/administrador", dados, {
          headers: { Authorization: token },
        });
        console.log(response);
          if (response.status === 200) {
            props.history.push("/administrador");
          }
        } catch (e) {
          alert("Ocorreu um erro. Tente de novo.");
        }
      } else {
        Swal.fire({
          position: 'flex-end',
          icon: 'warning',
          title: 'As senhas devem ser iguais',
          showConfirmButton: false,
          timer: 2000
        });
      }
    };

    const concluir = () => {
      isEdicao ? editar() : cadastrar();
    };

  return (
    <Container>
      <CorpoPagina>
        <FormContainer>
          <Link to="/admin">
            <BotaoForm texto="VOLTAR" ladoDireito={false} />
          </Link>

          <Caixa>
            <Subtitulo>{tipoPagina} DO ADMINISTRADOR</Subtitulo>
            <Titulo>Dados de Acesso</Titulo>

            <InputComRotulo
              texto="Nome"
              maxLength="100"
              name="nome"
              type="text"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />

            <InputComRotulo
              texto="Email"
              maxLength="60"
              name="email"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <InputComRotulo
              texto="Senha"
              maxLength="255"
              name="senha"
              type="password"
              value={valorSenha}
              onChange={(e) => setValorSenha(e.target.value)}
              required
              textoAlerta="Sua senha deve conter no mínimo 8 letras."
            />

            <InputComRotulo
              texto="Confirmar Senha"
              maxLength="255"
              name="confirmarSenha"
              type="password"
              value={valorConfirmarSenha}
              onChange={(e) => setValorConfirmarSenha(e.target.value)}
              required
            />
          </Caixa>

          <BotaoForm texto="Finalizar" concluir={true} criarJson={concluir} />
        </FormContainer>
      </CorpoPagina>
    </Container>
  );
}
