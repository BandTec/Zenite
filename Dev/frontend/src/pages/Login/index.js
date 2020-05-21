import React, {useState} from 'react';
import api from '../../services/api';

import {
  Container,
  Form,
  LogoZenite,
  RecuperarSenha,
  OpcoesAdicionais,
  Background,
} from "./styles";

import Botao from './Botao';
import Input from '../../components/InputComRotulo';
import Swal from 'sweetalert2';
import Logo from "../../assets/logos/logo4.png";

export default function Login(props) {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  async function handleSubmit(event) {
    event.preventDefault();
    try{
      const response = await api.post('/autentica/login', { email, senha });
      if(response.status===200){
        const { message } = response.data;
        localStorage.setItem('token', message);
        props.history.push("/dashboard");
      }else{
        Swal.fire({
          title:'Tente novamente',
          text:`Email ou senha errada.`,
          icon:'error',
          showConfirmButton: true,
        });
      }
    }catch(err){
      Swal.fire({
        title:'Tente novamente',
        text:`Email ou senha errada.`,
        icon:'error',
        showConfirmButton: true,
      });
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
          <input type="checkbox" /> Lembrar-se de mim
          <RecuperarSenha>Esqueceu a senha?</RecuperarSenha>
        </OpcoesAdicionais>
      </Form>

      <Background/>
    </Container>
  );
}