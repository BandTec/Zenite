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

//Autentica API OlhoVivo com o Token fornecido pelos mesmos
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
        if(value.numero == array[i-1].numero){
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
//Insere os pontos no banco de dados
async function insertPontos(array){
    try{
        await banco.desconectar();
        await banco.conectar();

        for (let index = 0; index < array.length; index++) {
            try {
                array[index] = array[index].replace("'", "''");
                await banco.sql.query(`insert into tbl_ponto_final (nome_terminal)
                values ('${array[index]}')`)
                // console.log(`Ponto ${array[index]} inserido com sucesso!`);
            } catch (err) {
                console.log(`Erro ao inserir ${array[index]}`)
            }    
            
        }

        await banco.desconectar();
        console.log("************************PONTOS INSERIDO***************************")
    }catch(err){
        console.log(`Erro na função: ${err.message}`)
    }
}
//Procura as FKs dos pontos no banco de dados
async function procurarForeignKeys(array){
    try{
        await banco.desconectar();
        await banco.conectar();

        let retorno = [];

        for (let index = 0; index < array.length; index++) {
            try {
                let respostaQuery = await banco.sql.query(`select * from tbl_ponto_final 
                where nome_terminal like '${array[index]}'`)
                retorno.push(respostaQuery.recordset[0]);
                // console.log(`Achei o ponto ${respostaQuery.recordset[0].nome_terminal} !`);
            } catch (err) {
                console.log(`Erro ao achar ${array[index]}`)
                console.log(err.message)
            }    
            
        }
        await banco.desconectar();
        return retorno;
    }catch(err){
        console.log(`Erro na função: ${err.message}`)
    }
}
//Compara o array com a linha e os seus pontos para achar qual a FK dos pontos
function acharFK(array, arrayFKs){
    try{
    for (let index = 0; index < array.length; index++) { 

        for (let i = 0; i < arrayFKs.length; i++) {
            if(array[index].terminalPrincipal==null){
                if(array[index].terminalSecundario == arrayFKs[i].nome_terminal){
                    array[index].terminalPFK = arrayFKs[i].id_ponto_final;
                    array[index].terminalSFK = arrayFKs[i].id_ponto_final;
                }
            }else{
                if(array[index].terminalPrincipal == arrayFKs[i].nome_terminal){
                    array[index].terminalPFK = arrayFKs[i].id_ponto_final;
                }
                if(array[index].terminalSecundario == arrayFKs[i].nome_terminal){
                    array[index].terminalSFK = arrayFKs[i].id_ponto_final;
                }
            }
        }
        // console.log(`Linha ${JSON.stringify(array[index])} resolvido`)
    }
    }catch(err){
        console.log(`Erro: ${err.message}`)
    }
}
//Insere a linha com as FKs de seus pontos
async function insertLinhas(array){
    try{
        await banco.desconectar();
        await banco.conectar();

        for (let index = 0; index < array.length; index++) {
            try {
                await banco.sql.query(`insert into tbl_linha (numero, fk_ponto_ida, fk_ponto_volta)
                values ('${array[index].numero}', ${array[index].terminalPFK}, ${array[index].terminalSFK})`)
                // console.log(`Linha ${array[index].numero} inserido com sucesso!`);
            } catch (err) {
                console.log(`Erro ao inserir: ${array[index].numero}\nErro: ${err.message}`)
            }    
            
        }

        await banco.desconectar();
        console.log("************************LINHAS INSERIDO***************************")
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
            
            //Variaveis complementares para formular o JSON
            let nroLinha;
            let terminal1;
            let terminal2;

            response.data.forEach((element) => {
                nroLinha = `${element.lt}-${element.tl}`;
                //Verifica a linha é uma circular
                if(element.tp=="CIRCULAR" || element.lc==true){
                    terminal1=null;
                }else{
                    terminal1=element.tp
                }

                terminal2 = element.ts;
                linhasPorTerminal.push({
                    numero: nroLinha,
                    Circular: element.lc,
                    terminalPrincipal: terminal1,
                    terminalPFK: -1,
                    terminalSecundario: terminal2,
                    terminalSFK: -1
                })
            });  
        }

        //Filtra as linhas para que não tenha duplicatas no array
        filtroLinha = linhasPorTerminal.filter(verificarDuplicatasLinhas);
        //Cria um novo array apenas com as linhas
        let linhas = filtroLinha.map(item =>(item.numero));

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
        
        //await insertPontos(pontos);

        //Procura as FKs dos pontos no Banco de dados
        let pontosComFKBanco = await procurarForeignKeys(pontos)
        //Verifica qual FK condiz com os pontos que a linha tem
        await acharFK(filtroLinha, pontosComFKBanco);
        //Insere a linha com as FK de seus pontos
        //await insertLinhas(filtroLinha);

        res.status(200).json({filtroLinha,linhas,pontos});
    }catch(err){
        console.log(err);
        res.status(500).json(err.message);
    }
});