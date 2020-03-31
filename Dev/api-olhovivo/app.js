const express = require('express');
const axios = require('axios');
const app = express();

const banco = require('./conexaoBanco');

const url = "http://api.olhovivo.sptrans.com.br/v2.1"
const tokenApi = "68b21b2faf9ec3708c35f900fa3d7a3cc735ee4c0bddcfe79fff54ba8dadd79b";

app.use(express.json());
app.listen(3000, () => {
    console.log(`Servidor rodando na porta 3000...`)
});

app.get('/', async (req, res) =>{
    res.send("Olá essa é a API de Consumo.");
})

async function autenticar() {
    try{
        let autenticado = false;
        do{
            const response = await axios.post(`${url}/Login/Autenticar?token=${tokenApi}`);
            autenticado = response.data;
        }while(autenticado==false);
        console.log("Autenticado");
    }catch(err){
        console.log(err.message)
    }
}

app.get("/cadastrarlinhas", async (req, res) => {
    try{
        await autenticar();
        const response = [];

        for (let posicao = 0; posicao < 9999; posicao++) {
            response.push(await axios.get(`${url}/Linha/Buscar?termosBusca=${posicao}`));
        }

        res.json(response);
    }catch(err){
        console.log(err);
        res.json(err.message);
    }
});
