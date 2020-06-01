'use strict';

const connection = require('../configs/connection')

class dispositivoModel {
    async registrarViagem(codigoDispositivo, serialNumber) {
        const sql = `
            EXEC sp_RegistraViagem_i @arduinoSerial = '${serialNumber}', @codigoDispositivo = '${codigoDispositivo}'
        `
        let list = await connection.query(sql)
        return list.recordsets[0]
    }

}

module.exports = dispositivoModel