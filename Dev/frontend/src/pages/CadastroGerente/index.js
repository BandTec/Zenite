import React, { useState, useEffect } from 'react';
import api from '../../services/api';

import { Container } from './styles';
import DadosPessoais from './dadosPessoais';
import DadosEndereco from './dadosEndereco';
import DadosAcesso from './dadosAcesso';
import Loader from '../../components/Loader'

export default function CadastroGerente(props) {

    const [validacaoSenha, setValidacaoSenha] = useState(false);
    const [pagina, setPagina] = useState(1);
    const [dados, setDados] = useState({});
    const caminho = props.match.path;
    const id = props.match.params.id;
    const isEdicao = caminho.includes("editar");
    const tipoPagina = isEdicao ? "Edição" : "Cadastro";
    
    const mudarPagina = isProximo => {
        isProximo ? setPagina(pagina+1) : setPagina(pagina-1);
    }

    const adicionarDados = novoDado => {
        setDados({ ...dados, ...novoDado });
    }

    useEffect(() => {
        async function consultarEdicao() {
          try {
            const token = localStorage.getItem("token");
            const response = await api.get(`/api/gerente/${id}`, {
              headers: { Authorization: token },
            });
            const dadosConsulta = response.data;
            setDados({...dadosConsulta});
          } catch (e) {
            alert("Ocorreu um erro. Tente de novo.");
          }
        }
        if(isEdicao){
           consultarEdicao();
        }
    }, [id]);


    const cadastrar = async () => {
        try{
            const token = await localStorage.getItem('token');
            
            const response = await api.post('/api/gerente', dados, {
                headers: {'Authorization': token}
            });

            if(response.status === 201){
                props.history.push("/gerente");
            }
        }catch (e) {
          alert("Ocorreu um erro. Tente de novo.");
          console.log("Erro no cadastro:"+e.message)
        }  
    }

    const editar = async () => {
        const token = await localStorage.getItem("token");
        try {
          if (validacaoSenha){
              const response = await api.put(`/api/gerente/${id}`, dados, {
                headers: { Authorization: token },
              });
          
            if (response.status === 200) {
              props.history.push("/gerente");
            } else {
              alert("Ocorreu um erro. Tente de novo");
            }
          }else {
              alert("Senha diferente, tente novamente.");
          }
    
        }catch(e) {
          alert("Ocorreu um erro. Tente de novo.");
          console.log("Erro na edicao:"+e.message);
        }
        
    };

    const chamada = () => {isEdicao ? editar() : cadastrar();}
    
    return (
        <Container>
            {
            isEdicao && Object.keys(dados).length === 0 ? (
                <Loader />
            ) : (   
                pagina === 1 ?
                    <DadosPessoais mudarPagina={mudarPagina} tipoPagina={tipoPagina}
                        adicionarDados={adicionarDados} dados={dados} />
                : pagina === 2 ?
                    <DadosEndereco mudarPagina={mudarPagina} tipoPagina={tipoPagina}
                        adicionarDados={adicionarDados} dados={dados} />
                : pagina === 3 ?
                    <DadosAcesso   mudarPagina={mudarPagina} tipoPagina={tipoPagina}
                        adicionarDados={adicionarDados} dados={dados} 
                        validarSenha={setValidacaoSenha} />
                : pagina === 4 ?
                    chamada()
                : <h1>Pagina não encontrada</h1>
            )}
        </Container>
    );
}
