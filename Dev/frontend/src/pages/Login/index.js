import React from 'react';

import { Container } from './styles';
import Form from '../../components/Form';
import Button from '../../components/Button';
import Input from '../../components/Input';

export default function Login() {
  return (
    <Container>
      <Form>
        <Input />
        <Button />
      </Form>
    </Container>
  );
}