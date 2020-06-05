'use strict';

const connection = require('../configs/connection')
const { alterarChamada } = require('../configs/arduino')
const { registrarViagem } = require('../controllers/dispositivoController')

class cartaoModel {

    async create(codigoCartao, res) {
      const sql = `            
      INSERT
      INTO tbl_dispositivo(codigo_dispositivo, fk_tipo)
      VALUES ('${codigoCartao}', 2)
      `

      await connection.query(sql)
      alterarChamada(registrarViagem)
      
      res.status(200).json("Cartão cadastrado com sucesso!")
    }

    async update(codigoCartao, res, id) {
        const sql = `            
        UPDATE tbl_dispositivo
           SET codigo_dispositivo = '${codigoCartao}'
         WHERE id_dispositivo = ${id}
        `
        await connection.query(sql)

        alterarChamada(registrarViagem)
      
        res.status(200).json("Cartão atualizado com sucesso!")
    }

    async delete(id) {
        const sql = `            
        DELETE
          FROM tbl_dispositivo
         WHERE id_dispositivo = ${id}
        `
        return await connection.query(sql)

    }

}

module.exports = cartaoModel