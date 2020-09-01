import { mostrarErro, mostrarSucesso, mostrarAlerta } from "src/functions/alertas";
import api from "../api";

export const cadastrar = async (values, history) => {
  try {
    const token = await localStorage.getItem("token");

    const response = await api.post("/api/gerente", values, {
      headers: { Authorization: token },
    });

    if (response.status === 201) {
      mostrarSucesso("Gerente cadastrado com sucesso!");
      history.goBack();
    } else {
      mostrarErro();
    }
  } catch (e) {
    mostrarErro();
  }
};

export const editar = async (values, history, id) => {
  const token = await localStorage.getItem("token");
  let result = await mostrarAlerta();

  if (result.value) {
    try {
      const response = await api.put(`/api/gerente/${id}`, values, {
        headers: { Authorization: token },
      });

      if (response.status === 200) {
        mostrarSucesso("Gerente editado com sucesso!");
        history.goBack();
      } else {
        mostrarErro();
      }
    } catch (e) {
      mostrarErro();
    }
  }
};

export async function consultarEdicao(id) {
  try {
    const token = localStorage.getItem("token");
    const response = await api.get(`/api/gerente/${id}`, {
      headers: { Authorization: token },
    });
    const dadosConsulta = response.data;
    delete dadosConsulta["linhas"];
    dadosConsulta.conta.senha = "";

    return dadosConsulta;
  } catch (e) {
    mostrarErro();
  }
}