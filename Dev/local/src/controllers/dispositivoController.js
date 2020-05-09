const DispositivoModel = require('../models/dispositivoModel')
const { ativarLeitorArduino, getDadosArduino } = require('../configs/arduino')

const iniciar = (req, res) => {
    ativarLeitorArduino().then( leitor => {
        leitor.on('data', codigoDispositivo => 
                            registrarViagem(codigoDispositivo.substring(1,12)))
        res.status(202).json("Arduino iniciado...")

    }).catch(error => {
        console.error(error.message)
        res.status(409).json(error.message)
    })
}

const registrarViagem = async codigoDispositivo => {

    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    
    model = new DispositivoModel()
    const resposta = await model.registrarViagem(codigoDispositivo, numeroSerialArduino)

    return resposta
}

module.exports = {
    registrarViagem,
    iniciar
}