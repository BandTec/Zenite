'use strict';

const connection = require('../configs/connection')

class cartaoModel {

    constructor(codigoCartao){
        this.codigoCartao = codigoCartao
    }

    async create() {
        const sql = `            
        INSERT
          INTO tbl_dispositivo(codigo_dispositivo, fk_tipo)
        VALUES ('${this.codigoCartao}', 2)
        `
        return await connection.query(sql)

    }

    async update(id) {
        const sql = `            
        UPDATE 
          FROM tbl_dispositivo
           SET codigo_dispositivo = '${this.codigoCartao}'
         WHERE id_dispositivo = ${id}
        `
        return await connection.query(sql)

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