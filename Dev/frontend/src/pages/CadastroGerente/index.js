import React, { useState } from 'react';

import { Container } from './styles';
import DadosPessoais from './dadosPessoais';
import DadosEndereco from './endereco';
import DadosAcesso from './dadosAcesso';

export default function CadastroGerente(props) {

    const [pagina, setPagina] = useState(1);
    const caminho = props.match.path;
    const tipoPagina = caminho.includes("editar") ? "Edição" : "Cadastro";
    
    const mudarPagina = isProximo => {
        isProximo ? setPagina(pagina+1) : setPagina(pagina-1);
    }

    const cadastrar = () =>{
        console.log("Aqui vai o cadastro")
    }
    
    return (
        <Container>
            {   pagina === 1 ?
                    <DadosPessoais mudarPagina={mudarPagina} tipoPagina={tipoPagina} />
                : pagina === 2 ?
                    <DadosEndereco mudarPagina={mudarPagina} tipoPagina={tipoPagina} />
                : pagina === 3 ?
                    <DadosAcesso   mudarPagina={mudarPagina} tipoPagina={tipoPagina} />
                : pagina === 4 ?
                    cadastrar()
                : <h1>Pagina não encontrada</h1>
            }
        </Container>
    );
}
