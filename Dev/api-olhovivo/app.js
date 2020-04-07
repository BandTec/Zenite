const express = require('express');
const axios = require('axios');
const app = express();

const banco = require('./conexaoBanco');

const url = "http://api.olhovivo.sptrans.com.br/v2.1"
const tokenApi = "68b21b2faf9ec3708c35f900fa3d7a3cc735ee4c0bddcfe79fff54ba8dadd79b";
let cookie;

//NÃO OUSAR TOCAR NESSA PARTE
//****************************************************
const terminais = ["CID. TIRADENTES","METRÔ ITAQUERA","CARRÃO",
"BRITANIA","PIRITUBA",
"CASA VERDE","CACHOEIRINHA",
"CARVALHO","ARICANDUVA","PENHA","SÃO MIGUEL",
"SÃO MATEUS","SACOMÃ","SAPOPEMBA",
"GRAJAÚ","PARELHEIROS","VARGINHA",
"ÁGUA ESPRAIADA","CAPELINHA","GUARAPIRANGA","JARDIM ÂNGELA","JOÃO DIAS","STO. AMARO",
"CAMPO LIMPO",
"AMARAL GURGEL","BANDEIRA","LAPA","MERCADO","TERM. PQ. D. PEDRO II","PINHEIROS","PRINC. ISABEL","VL PRUDENTE"]
//****************************************************

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
            const res = await axios.post(`${url}/Login/Autenticar?token=${tokenApi}`);
            autenticado = true;

            cookie = res.headers["set-cookie"];
            let aux = cookie[0].split(";");
            cookie = aux[0];

        }while(autenticado==false);
        console.log("Autenticado");
    }catch(err){
        console.log(err.message)
    }
}

app.get("/cadastrarlinhas", async (req, res) => {
    try{
        await autenticar();
        let linhasPorTerminal = []
        for(var i = 0; i < terminais.length; i++){
             let response = await axios.get(
               `${url}/Linha/Buscar?termosBusca=${terminais[i]}`,
               { headers: { Cookie: cookie } }
             );

             response.data.map((element) => {
               linhasPorTerminal.push(element);
             });
        }
        res.status(200).json(linhasPorTerminal);
    }catch(err){
        console.log(err);
        res.status(500).json(err.message);
    }
});

app.get("/teste", async (req, res) => {
    try{
        await autenticar();
        const response = await axios.get(`${url}/Linha/Buscar?termosBusca=VL PRUDENTE`, 
            {headers: {Cookie: cookie}});
        res.status(200).json(response.data);
    }catch(err){
        console.log(err);
        res.status(500).json(err.message);
    }
});