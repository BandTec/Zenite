import {
  mostrarErro,
  mostrarSucesso,
  mostrarAlerta,
} from "../../functions/alertas";
import api from "../../services/api";

export const editar = async (url, values, history) => {
  const token = await localStorage.getItem("token");
  let result = await mostrarAlerta();

  if (result.value) {
    try {
      const response = await api.put(url, values, {
        headers: { Authorization: token },
      });

      if (response.status === 200) {
        mostrarSucesso("Edição realizada com sucesso!");
        history.goBack();
      } else {
        mostrarErro();
      }
    } catch (e) {
      mostrarErro();
    }
  }
};
