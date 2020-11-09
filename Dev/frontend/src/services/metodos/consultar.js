import api from "../../services/api";

export default async function consultar(url, formatarDados) {
  try {
    const token = localStorage.getItem("token");
    const response = await api.get(url, {
      headers: { Authorization: token },
    });
    let dados = response.data;

    let temp = [];
    dados.lista.forEach((item) => {
      temp.push(formatarDados(item));
    });

    return {
      dados: temp,
      totalPaginas: dados.totalPaginas,
      totalItens: dados.totalItens,
      rawData: dados.lista,
    };
  } catch (e) {
    console.log(e);
    return {
      dados: [],
      totalPaginas: 0,
      totalItens: 0,
    };
  }
}
