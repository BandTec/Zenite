import axios from 'axios';
import Swal from "sweetalert2";

export const viacep = async (cepValue) => {
    /* if(cepValue.includes('-')){
        Swal.fire("Erro!", "CEP inválido", "error");
        return {erro: true};
    } */
    const response = await axios.get(`https://viacep.com.br/ws/${cepValue}/json`);
    if(response.data.erro){
        Swal.fire("Erro!", "CEP inválido", "error");
        return {erro: true};
    }
    const { logradouro, localidade, uf } = response.data;
    return { erro: false, logradouro, localidade, uf };
}