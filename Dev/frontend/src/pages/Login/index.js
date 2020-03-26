import React from 'react';

import { Container } from './styles';
import Form from '../../components/Form';
import Botao from '../../components/Botao';
import Input from '../../components/Input';

export default function Login() {
  return (
    <Container>
      <Form>
        Email
        <Input />
        Senha
        <Input />
        <Botao descricao='Entrar' estiloEscuro={true}/>
      </Form>
    </Container>
  );
}