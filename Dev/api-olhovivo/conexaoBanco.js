const sql = require('mssql');

const configBanco = {
    server: "svzenite.database.windows.net",
    user: "zeniteadmin",
    password: "#Gfgrupo7",
    database: "bdZenite",
    options: {
        encrypt: true,
        enableArithAbort: true
    }
}

sql.on('error', err => {
    console.error(`Erro de Conexão: ${err}`);
});

const conectar = async () => {
    try{
        await sql.connect(configBanco);
        console.log("Conexão com o banco relizada!")
    }catch(err){
        console.log(err.message)
    }
};

const desconectar = async () => {
    try{
        await sql.close();
        console.log("Conexão fechada!");
    }catch(err){
        console.log(err.message);
    }
}

module.exports = { conectar, desconectar, sql };