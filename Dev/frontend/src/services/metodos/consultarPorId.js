import { mostrarErro } from "../../functions/alertas";
import api from "../../services/api";

export async function consultarEdicao(url) {
  try {
    const token = localStorage.getItem("token");
    const response = await api.get(url, {
      headers: { Authorization: token },
    });

    const dadosConsulta = response.data;
    return dadosConsulta;
  } catch (e) {
    mostrarErro();
  }
}
