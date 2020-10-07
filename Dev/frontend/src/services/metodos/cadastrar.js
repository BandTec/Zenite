import { mostrarErro, mostrarSucesso } from "../../functions/alertas";
import api from "../../services/api";

export const cadastrar = async (url, values, history) => {
  try {
    const token = await localStorage.getItem("token");

    const response = await api.post(url, values, {
      headers: { Authorization: token },
    });

    if (response.status === 201) {
      mostrarSucesso("Cadastrado realizado com sucesso!");
      history.goBack();
    } else {
      mostrarErro();
    }
  } catch (e) {
    mostrarErro();
  }
};
