import styled from 'styled-components';

export const Container = styled.div`
  min-width: 100vw;
  min-height: 100vh;
  background-color: var(--azulMenu);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  h1 {
    font-size: 2.5em;
    color: var(--azulBemMaisEscuro);
    font-weight: 800;
  }
`;
