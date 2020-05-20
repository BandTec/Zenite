import React from 'react';
import {Link} from 'react-router-dom';
import { StyledTableCell } from '../styles';
import { Img, Alinhar } from "./styles";
import EditarIcon from './../../../assets/icons/editar.svg'
import DetalhesIcon from "./../../../assets/icons/detalhes.svg";
import ExcluirIcon from "./../../../assets/icons/excluir.svg";
import api from "./../../../services/api";
import Swal from 'sweetalert2';

export default function Acoes({ id, tipo, detalhes = true }) {

  const excluir = async () => {
      Swal.fire({
      title: 'Aviso',
      text: 'Deseja realmente excluir este dado? ',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText:'Sim, desejo',
      cancelButtonText: 'Não',
    }).then(async (result) => {

      

      if (result.value) {
          Swal.fire({
          title: 'Aguarde um momento',
          timer: 2500,
          showConfirmButton: false,
          timerProgressBar: true
        });
          const token = localStorage.getItem('token');     
          const response = await api.delete(`/api/${tipo}/${id}`,
            {
              headers: {'Authorization': token}
            }
          );

        if (response.status === 200) {
            Swal.fire({
            title: 'Tudo Pronto',
            text: 'Dado excluido com sucesso',
            icon:'success',
            showConfirmButton: false,
            });
          window.location.reload();
        } else {
             Swal.fire({
            title:'Tente novamente',
            text:'Ocorreu um imprevisto, por gentileza tente novamente.',
            icon:'error',
            showConfirmButton: false,
             });
        }
    }
    });
  }

  return (
    <StyledTableCell align="left">
      <Alinhar>
     {detalhes && 
      <Link to={`/${tipo}/detalhes/${id}`}>
          <Img src={DetalhesIcon} title="Ver detalhes" />
        </Link>
    }

        <Link to={`/${tipo}/editar/${id}`}>
          <Img src={EditarIcon} title="Editar" />
        </Link>

        <button onClick={excluir}>
          <Img src={ExcluirIcon} title="Excluir dado" />
        </button>
      </Alinhar>
    </StyledTableCell>
  );
}
