import React from 'react';

import { Container } from './styles';
import Form from '../../components/Form';
import Botao from '../../components/Botao';
import Input from '../../components/InputComRotulo';

export default function Login() {
  return (
    <Container>
      <Form>
        <Input texto="Email" tamanho={330}/>
        <Input texto="Senha" type="password" tamanho={330}/>
        <Botao descricao="Entrar" estiloEscuro={true} tamanho={330}/>
      </Form>
    </Container>
  );
}