'use strict';

const connection = require('../configs/connection')

class arduinoModel {
    async registrarViagem(codigoDispositivo, serialNumber) {
        const sql = `
            SELECT count(id_dispositivo) FROM tbl_dispositivo WHERE codigo_dispositivo = '${codigoDispositivo}'
        `
        let list = await connection.query(sql)
        return list.recordsets[0]
    }
}

module.exports = arduinoModel