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

//Função para verificar duplicatas nas linhas que retornaram da API
function verificarDuplicatasLinhas(value, index, array){
    let NaoExiste = true;
    for (let i = index ;i > 0 ; i--) {
        if(value.NumeroLinha==array[i-1].NumeroLinha){
            NaoExiste=false;
        }
    }

    if(NaoExiste){
        return true;
    }else{
        return false;
    }
}

//Função para verificar duplicatas nos terminais que retornaram da API
function verificarDuplicatasPontos(value , index, array){
    let NaoExiste = true;
    for (let i = index ;i > 0 ; i--) {
        if(value==array[i-1]){
            NaoExiste=false;
        }
    }
    if(NaoExiste){
        return true;
    }else{
        return false;
    }
}

async function insertPontos(array){
    try{
        await banco.desconectar();
        await banco.conectar();

        for (let index = 0; index < array.length; index++) {
            try {
                await banco.sql.query(`insert into tblPontoFinal (nomeTerminal)
                values ('${array[index]}')`)
                console.log(`Ponto ${array[index]} inserido com sucesso!`);
            } catch (err) {
                console.log(`Erro ao inserir ${array[index]}`)
            }    
            
        }

        await banco.desconectar();
        console.log("************************TUDO INSERIDO***************************")
    }catch(err){
        console.log(`Erro na função: ${err.message}`)
    }
}

async function insert(array){
    try{
        await banco.desconectar();
        await banco.conectar();

        for (let index = 0; index < array.length; index++) {
            try {
                await banco.sql.query(`insert into tblPontoFinal (nomeTerminal)
                values ('${array[index]}')`)
                console.log(`Ponto ${array[index]} inserido com sucesso!`);
            } catch (err) {
                console.log(`Erro ao inserir ${array[index]}`)
            }    
            
        }

        await banco.desconectar();
        console.log("************************TUDO INSERIDO***************************")
    }catch(err){
        console.log(`Erro na função: ${err.message}`)
    }
}

app.get("/cadastrarlinhas", async (req, res) => {
    try{
        await autenticar();
        let linhasPorTerminal = []
        let filtroLinha = [];
        let filtroTerminal = [];
        
        /*
        For que percorre o array de terminais da SPTRANS e passa para a API para
        obter todas as linhas.
        */
        for(let i = 0; i < terminais.length; i++){
            
            let response = await axios.get(
               `${url}/Linha/Buscar?termosBusca=${terminais[i]}`,
                { headers: { Cookie: cookie } }
            );

            let nroLinha;
            let terminal1;
            let terminal2;

            response.data.forEach((element) => {
                //linhasPorTerminal.push(element);
                nroLinha = `${element.lt}-${element.tl}`;

                if(element.tp=="CIRCULAR" || element.lc==true){
                    terminal1=null;
                }else{
                    terminal1=element.tp
                }

                terminal2 = element.ts;
                linhasPorTerminal.push({
                    NumeroLinha: nroLinha,
                    Circular: element.lc,
                    terminalPrincipal: terminal1,
                    terminalSecundario: terminal2
                })
            });  
        }

        //Filtra as linhas para que não tenha duplicatas no array
        filtroLinha = linhasPorTerminal.filter(verificarDuplicatasLinhas);
        //Cria um novo array apenas com as linhas
        let linhas = filtroLinha.map(item =>(item.NumeroLinha));

        //Popula o array "filtroTerminal" com os terminais e pontos
        filtroLinha.forEach(item => {
            if(item.terminalPrincipal!=null){
                filtroTerminal.push(item.terminalPrincipal);
            }
            filtroTerminal.push(item.terminalSecundario);
        });
        
        //Une terminal principal e terminal secundario em um array auxiliar
        let auxiliar = filtroTerminal.map(item => (item))
        //Filtra os terminais para que não tenha duplicatas
        let pontos = auxiliar.filter(verificarDuplicatasPontos)
        
      
        res.status(200).json({filtroLinha,linhas,pontos});
    }catch(err){
        console.log(err);
        res.status(500).json(err.message);
    }
});

app.get("/teste", async (req, res) => {
    try{
        await autenticar();
        let linhasPorTerminal = []
        let filtroLinha = [];
        let filtroTerminal = [];

        for(let i = 0; i < terminais.length; i++){
            
            let response = await axios.get(
               `${url}/Linha/Buscar?termosBusca=${terminais[i]}`,
                { headers: { Cookie: cookie } }
            );

            let nroLinha;
            let terminal1;
            let terminal2;

            response.data.forEach((element) => {
                //linhasPorTerminal.push(element);
                nroLinha = `${element.lt}-${element.tl}`;

                if(element.tp=="CIRCULAR" || element.lc==true){
                    terminal1=null;
                }else{
                    terminal1=element.tp
                }

                terminal2 = element.ts;
                linhasPorTerminal.push({
                    NumeroLinha: nroLinha,
                    Circular: element.lc,
                    terminalPrincipal: terminal1,
                    terminalSecundario: terminal2
                })
            });  
        }

        res.status(200).json(linhasPorTerminal);
    }catch(err){
        console.log(err);
        res.status(500).json(err.message);
    }
});