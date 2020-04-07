import React, {useState} from 'react';
import api from '../../services/api';

import { Container } from './styles';
import Form from '../../components/Form';
import Botao from './Botao';
import Input from '../../components/InputComRotulo';

export default function Login(props) {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  async function handleSubmit(event) {
    event.preventDefault();
    const response = await api.post('/autentica/login', { email, senha });
    if(response.status){
      const { message } = response.data;
      localStorage.setItem('token', message);
      props.history.push("/dashboard");
    }
  }

  return (
    <Container>
      <Form handleSubmit={handleSubmit}>
        <Input texto="Email" value={email} tamanho={330} onChange={event => setEmail(event.target.value)}/>
        <Input texto="Senha" value={senha} type="password" tamanho={330} onChange ={event => setSenha(event.target.value)}/>
        <Botao descricao="Entrar" estiloEscuro={true} tamanho={330}/>
      </Form>
    </Container>
  );
}