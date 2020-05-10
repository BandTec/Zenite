'use strict';

const connection = require('../configs/connection')

class arduinoModel {

    constructor(numeroSerialArduino){
        this.numeroSerialArduino = numeroSerialArduino
    }

    async create() {
        const sql = `            
        INSERT
          INTO tbl_dispositivo(codigo_dispositivo, fk_tipo)
        VALUES ('${this.numeroSerialArduino}', 2)
        `
        return await connection.query(sql)

    }

    async update(id) {
        const sql = `            
        UPDATE tbl_dispositivo
           SET codigo_dispositivo = '${this.numeroSerialArduino}'
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

module.exports = arduinoModel