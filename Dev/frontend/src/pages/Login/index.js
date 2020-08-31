import React, {useState} from 'react';
import api from '../../services/api';
// import { isAuthenticated } from '../../services/auth';

import {
  Container,
  Form,
  LogoZenite,
  RecuperarSenha,
  OpcoesAdicionais,
  Background,
} from "./styles";

import Botao from "./Botao";
import Input from "../../components/InputComRotulo";
import Swal from "sweetalert2";
import Logo from "assets/logos/logo4.png";

export default function Login(props) {
  
  
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  
  
  async function handleSubmit(event) {
    event.preventDefault();
    Swal.fire({
      title: "Aguarde um momento",
      timer: 2500,
      showConfirmButton: false,
      timerProgressBar: true,
    });
    try {
      const response = await api.post("/autentica/login", { email, senha });
      if (response.status === 200) {
        const { message } = response.data;

        localStorage.setItem("token", message);

        const userLogado = await api.get(`/logado`, {
          headers: { Authorization: message },
        });

        if (userLogado.status === 200) {
          localStorage.setItem("nivel", userLogado.data.conta.nivel.id);
          localStorage.setItem("nome", userLogado.data.nome);
          localStorage.setItem("id", userLogado.data.conta.nivel.id); 
          props.history.push({
            pathname: "/dashboard",
            state: { nivel: userLogado.data.conta.nivel.id },
          });
        }
      } else {

        Swal.fire({
          title: "Tente novamente",
          text: `Email ou senha errada.`,
          icon: "error",
          showConfirmButton: true,
        });
      }
    } catch (err) {
      Swal.fire({
        title: "Tente novamente",
        text: `Email ou senha errada.`,
        icon: "error",
        showConfirmButton: true,
      });
    }
  }
       
  async function recuperar() {
    const { value: email } = await Swal.fire({
        title: 'Informe seu e-mail cadastrado.',
        text: 'Enviaremos um e-mail para você !',
        input: 'email',
    })
    const response = await api.get("/esqueci-senha/{email}");
    console.log(response);
    if (response.status === 200) {
        if (email) {
            Swal.fire({
                title: "E-mail enviado com sucesso para : ",
                text: `email: ${email}`,
                icon: "success'",
                showConfirmButton: true,
            });
        } else {
            Swal.fire({
                title: "Tente novamente",
                text: `Email inválido ! Digite Novamente`,
                icon: "warning",
                showConfirmButton: true,
            });
        }
    }
}
  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <LogoZenite src={Logo} />
        <Input
          texto="Email"
          value={email}
          tamanho={330}
          onChange={(event) => setEmail(event.target.value)}
        />
        <Input
          texto="Senha"
          value={senha}
          type="password"
          tamanho={330}
          onChange={(event) => setSenha(event.target.value)}
        />
        <Botao descricao="Entrar" estiloEscuro={true} tamanho={330} />
        <OpcoesAdicionais>
          {/* <input type="checkbox" /> Lembrar-se de mim */}
          <RecuperarSenha onClick= {recuperar}>Esqueceu a senha?</RecuperarSenha>
        </OpcoesAdicionais>
      </Form>

      <Background />
    </Container>
  );
}
