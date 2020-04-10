import React, { useState, useEffect } from 'react';

import { Container } from './styles';
import DadosPessoais from './dadosPessoais';
import DadosEndereco from './endereco';
import DadosAcesso from './dadosAcesso';

export default function CadastroFiscal(props) {
    const { id } = props.match.params;
    const [pagina, setPagina] = useState(id);
    const caminho = props.match.path;
    const tipoPagina = caminho.includes("editar") ? "Edição" : "Cadastro";
    const tipoUrl = caminho.includes("editar") ? "editar" : "cadastro";
    
    return (
        <Container>
            {   
                id === 1 ?
                <DadosPessoais pagina={pagina} setPagina={setPagina} tipoPagina={tipoPagina} 
                tipoUrl={tipoUrl}/>
                : id === 2 ?
                <DadosEndereco pagina={pagina} setPagina={setPagina} tipoPagina={tipoPagina} 
                tipoUrl={tipoUrl}/>
                : id === 3 ?
                <DadosAcesso   pagina={pagina} setPagina={setPagina} tipoPagina={tipoPagina} 
                tipoUrl={tipoUrl}/>
                : <h1>Pagina não encontrada</h1>
            }
        </Container>
    );
}
