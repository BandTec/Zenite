import React from 'react';
import {Link} from 'react-router-dom';
import { StyledTableCell } from '../styles';
import { Img, Alinhar } from "./styles";
import EditarIcon from './../../../assets/icons/editar.svg'
import DetalhesIcon from "./../../../assets/icons/detalhes.svg";
import ExcluirIcon from "./../../../assets/icons/excluir.svg";
import api from "./../../../services/api";

export default function Acoes({ id, tipo, detalhes = true, editarFuncao }) {

  const excluir = async () => {
     const continuar = window.confirm("Deseja realmente excluir dado? ");  
     if (continuar) {
      const token = localStorage.getItem('token');     
      const response = await api.delete(`/api/${tipo}/${id}`,
        {
          headers: {'Authorization': token}
        }
      );

        if (response.status === 200) {
          alert("Dado excluido com sucesso!");
        }
     }
  }

  return (
    <StyledTableCell align="left">
      <Alinhar>
        {detalhes && (
          <Link to={`/${tipo}/detalhes/${id}`}>
            <Img src={DetalhesIcon} title="Ver detalhes" />
          </Link>
        )}

        {editarFuncao ? (
          <button onClick={() => editarFuncao(id)}>
            <Img src={EditarIcon} title="Editar" />
          </button>
        ) : (
          <Link to={`/${tipo}/editar/${id}`}>
            <Img src={EditarIcon} title="Editar" />
          </Link>
        )}
        <button onClick={excluir}>
          <Img src={ExcluirIcon} title="Excluir dado" />
        </button>
      </Alinhar>
    </StyledTableCell>
  );
}
