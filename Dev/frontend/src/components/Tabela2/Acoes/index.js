import React from 'react';
import {Link} from 'react-router-dom';
import { StyledTableCell } from '../styles';

export default function Acoes({ id }) {
  return (
    <StyledTableCell align="left">
        <Link to={`/fiscal/excluir/${id}`}>excluir</Link>
        <Link to={`/fiscal/editar/${id}`}>editar</Link>
  </StyledTableCell>
  );
}
