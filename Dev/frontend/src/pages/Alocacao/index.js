import React, { useState, useEffect } from "react";
import api from "../../services/api";
import AsyncSelect from "react-select/async";
import Swal from "sweetalert2";
import {
  Container,
  Nav,
  Row,
  Col,
  Titulo,
  Subtitulo,
  Texto,
  InfoTitle,
} from "./styles";
import Botao from "../../components/Botao";
import Detalhes from "./Detalhes";

export default function Alocacao() {
  const [selecionado, setSelecionado] = useState("");
  const [alocacao, setAlocacao] = useState("Escolha");
  const [alocar, setAlocar] = useState("");
  const [local, setLocal] = useState("");

  const trocarAlocacao = (e) => {
    setSelecionado(e.target.value);
  };

  useEffect(() => {
    setAlocar("");
    setLocal("");
    if (selecionado === "Fiscal" || selecionado === "Ônibus") {
      setAlocacao("Linha");
    } else if (selecionado === "Motorista") {
      setAlocacao("Ônibus");
    } else {
      setAlocacao("Escolha");
    }
  }, [selecionado]);

  const pesquisarOpcoes = async (inputValue, rota) => {
    const token = localStorage.getItem("token");
    let url = rota.toLowerCase();
    
    if (url === "ônibus") {
      url = "onibus";
    }
    const response = await api.get(`/api/${url}?q=${inputValue}`, {
      headers: { Authorization: token },
    });

    let options = response.data.map((item) => ({
      value: item.id,
      label: item.numero || item.nome,
      dados: item,
    }));

    return options;
  };

  const salvar = async () => {
    if (!alocar.dados || !local.dados) {
       Swal.fire({
         title: "Por favor escolha os dados a serem alocados",
         confirmButtonText: "Ok",
       });
      return;
    }

    let url = "";
    let corpo_requisicao = {};
    switch (selecionado) {
      case "Fiscal":
        url = "/api/fiscal/linhas";
        corpo_requisicao = {
          idFiscal: alocar.dados.id,
          idLinha: local.dados.id,
        };
        break;
      case "Motorista":
        url = "/api/motorista/onibus";
        corpo_requisicao = {
          idMotorista: alocar.dados.id,
          idCarro: local.dados.id,
        };
        break;
      case "Ônibus":
        url = "/api/onibus/linhas";
        corpo_requisicao = {
          idLinha: local.dados.id,
          idCarro: alocar.dados.id,
        };
        break;
      default:
        Swal.fire({
          title: "Por favor escolha os dados a serem alocados",
          confirmButtonText: "Ok",
        });
        return;
    }

    if (!!url && !!corpo_requisicao) {
      try {
        const token = localStorage.getItem("token");

        const response = await api.post(url, corpo_requisicao, {
          headers: { Authorization: token },
        });

        if (response.status === 201) {
          Swal.fire("Sucesso!", "Alocado com sucesso.", "success");
          switch (selecionado) {
            case "Fiscal":
                let linhas = alocar.dados.linhas;
                linhas.push(local.dados);
                setAlocar({...alocar, dados: {...alocar.dados, linhas: linhas}})        
              break;
            case "Motorista":
             let carros = alocar.dados.carros;
             carros.push(local.dados);
             setAlocar({
               ...alocar,
               dados: { ...alocar.dados, carros: carros },
             });        
              break;
            case "Ônibus":
               let linhasOnibus = alocar.dados.linhasId;
               linhasOnibus.push(alocar.dados.id);
               setAlocar({
                 ...alocar,
                 dados: { ...alocar.dados, linhasId: linhasOnibus },
               });  
              break;
            default:
             
          }
        } else {
          Swal.fire("Erro!", "Tente novamente.", "error");
        }
      } catch (erro) {
        console.log(erro);
        Swal.fire("Erro!", "Tente novamente.", "error");
      }
    }
  };

  const encurtarNome = (e) => {
    if (e.length > 15) {
      return e.substring(0, e.indexOf(" "));
    }
    return e;
  };

  return (
    <Container>
      <div>
        <Subtitulo>Cadastro</Subtitulo>
        <Titulo>Alocacao</Titulo>
      </div>
      <Nav>
        <div>
          <Texto>Alocar</Texto>
          <select value={selecionado} onChange={trocarAlocacao}>
            <option>Escolha</option>
            <option>Fiscal</option>
            <option>Motorista</option>
            <option>Ônibus</option>
          </select>
          <InfoTitle>{alocar.label && encurtarNome(alocar.label)}</InfoTitle>
        </div>
        <div>
          <Texto>A um{alocacao === "Linha" ? "a" : ""}</Texto>

          <Titulo>{alocacao}</Titulo>
          <InfoTitle>{local.label}</InfoTitle>
        </div>
        <Botao descricao="Alocar" onClick={salvar} />
      </Nav>

      {selecionado && (
        <Row>
          <Col>
            <AsyncSelect
              placeholder="Digite aqui o termo de sua pesquisa..."
              value={alocar}
              onChange={(e) => setAlocar(e)}
              loadOptions={(e) => pesquisarOpcoes(e, selecionado)}
            />

            {!!alocar && <Detalhes tipo={selecionado} objeto={alocar} />}
          </Col>

          <Col>
            <AsyncSelect
              placeholder="Digite aqui o termo de sua pesquisa..."
              value={local}
              onChange={(e) => setLocal(e)}
              loadOptions={(e) => pesquisarOpcoes(e, alocacao)}
            />
            {!!local && <Detalhes tipo={alocacao} objeto={local} /> }
          </Col>
        </Row>
      )}
    </Container>
  );
}
