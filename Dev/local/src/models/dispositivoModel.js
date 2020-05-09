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

    async cadastrarArduino(serialNumber) {
        const sql = `
            INSERT INTO tbl_dispositivo(codigo_dispositivo, fk_tipo)
            VALUES ('${serialNumber}', 2)
        `
        return await connection.query(sql)

    }

}

module.exports = dispositivoModel