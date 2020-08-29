import Swal from "sweetalert2";

export const mostrarErro = (mensagemCustomizada) => {
  let mensagemPadrao =
    "Ocorreu um imprevisto, por gentileza tente novamente.";
  let mensagem = mensagemCustomizada ? mensagemCustomizada : mensagemPadrao;
  Swal.fire({
    title: "Tente novamente",
    text: mensagem,
    icon: "error",
    showConfirmButton: false,
  });
};

export const mostrarSucesso = (mensagemCustomizada) => {
  let mensagemPadrao = "Registrado com sucesso";
  let mensagem = mensagemCustomizada ? mensagemCustomizada : mensagemPadrao;
  Swal.fire({
    title: "Sucesso!",
    text: mensagem,
    icon: "success",
    showConfirmButton: true,
  });
};

export const mostrarAlerta = async (mensagemCustomizada) => {
  let mensagemPadrao = "Deseja realmente editar este dado? ";
  let mensagem = mensagemCustomizada ? mensagemCustomizada : mensagemPadrao;
  return await Swal.fire({
    title: "Aviso",
    text: mensagem,
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sim, desejo",
    cancelButtonText: "Não",
  });
};

