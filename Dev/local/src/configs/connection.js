'use strict'

var sql = require('mssql')
var config = require("./config")
var isNull = require("./script").isNull

const query = queryString => {

	// Verifica se a queryString veio vazia
	if(isNull(queryString)){
		return null

	}else{
		// Fecha alguma conexão caso haja alguma aberta
		sql.close()

		return new Promise((resolve, reject) => {

			console.log('Conectando ao banco de dados...')

			// Executa a chamada ao banco
			sql.connect(config).then(pool => {
				return pool.request().query(queryString)

			}).then(results => {
				// Fecha a conexão e retorna o resultado
				console.log('Query efetuada com sucesso!')
				sql.close()
				resolve(results)

			}).catch(error => {
				// Caso aconteça algum erro, exibi e fecha a conexão
				console.log('Erro ao executar a query', error)
				sql.close()
				reject(error)
			})
		})
	}
}

module.exports = {
	query
}