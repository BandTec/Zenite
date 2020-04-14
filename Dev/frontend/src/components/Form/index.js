import React from 'react';

import Logo from '../../assets/logos/logo4.png';
import { Container, LogoZenite ,RecuperarSenha, OpcoesAdicionais } from './styles';

export default function Form({children, handleSubmit}) {
  return (      
    <Container onSubmit={handleSubmit}>
      <LogoZenite src={Logo} />
        {children}
        <OpcoesAdicionais>
          <input type="checkbox"/> Lembrar-se de mim
          <RecuperarSenha>Esqueceu a senha?</RecuperarSenha>
        </OpcoesAdicionais>
    </Container>
  );
}
