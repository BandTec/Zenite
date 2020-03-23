import React from 'react';

import { Container } from './styles';
import Form from '../../components/Form';
import Button from '../../components/Button';
import Input from '../../components/Input';

export default function Login() {
  return (
    <Container>
      <Form>
        Email
        <Input />
        Senha
        <Input />
        <Button text='Entrar'/>
      </Form>
    </Container>
  );
}