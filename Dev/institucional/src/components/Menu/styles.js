import styled from 'styled-components';

export const Navbar = styled.nav`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background: #2A3180;
  width: 100vw;
  padding: 15px 50px;
  position: fixed;
  top: 0px;
  border-bottom: 1px solid #2A3180;

  img {
    width: 85%;
  }
`;

export const ListItens = styled.ul`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  li {
    list-style: none;
    padding: 0px 10px;
  }

  a {
    color: #fff;
    font-size: 0.8em;
    font-weight: 700;
    letter-spacing: .05em;
    transition: all .3s ease-in-out;
  }

  a:hover {
    color: var(--azulClaro);
  }
`;
