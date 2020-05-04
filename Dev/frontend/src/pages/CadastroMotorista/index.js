import React, { useState, useEffect } from 'react';
import api from '../../services/api';

import { Container } from './styles';
import DadosPessoais from './dadosPessoais';
import DadosEndereco from './endereco';
import DadosAcesso from './dadosAcesso';

export default function CadastroMotorista(props) {

    const [pagina, setPagina] = useState(1);
    const [dados, setDados] = useState({});
    const caminho = props.match.path;
    const tipoPagina = caminho.includes("editar") ? "Edição" : "Cadastro";
    
    const mudarPagina = isProximo => {
        isProximo ? setPagina(pagina + 1) : setPagina(pagina - 1);
    }

    const adicionarDados = novoDado => {
        setDados({ ...dados, ...novoDado });
    }

    const cadastrar = async () => {
        const token = await localStorage.getItem('token');

        const response = await api.post('/api/motorista', dados, {
            headers: {'Authorization': token}
          });

        if(response.status){
            props.history.push("/motorista");
        }

    }

    const chamada = () => {cadastrar()}
   
    return (
        <Container>

            { pagina === 1 ?
                <DadosPessoais mudarPagina={mudarPagina} tipoPagina={tipoPagina}
                    adicionarDados={adicionarDados} />
                : pagina === 2 ?
                    <DadosEndereco mudarPagina={mudarPagina} tipoPagina={tipoPagina}
                        adicionarDados={adicionarDados} />
                    : pagina === 3 ?
                        <DadosAcesso mudarPagina={mudarPagina} tipoPagina={tipoPagina}
                            adicionarDados={adicionarDados} />
                        : pagina === 4 ?
                            chamada()
                            : <h1>Pagina não encontrada</h1>
            }
        </Container>
    );
}