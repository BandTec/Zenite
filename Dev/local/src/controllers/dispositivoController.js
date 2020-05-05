const DispositivoModel = require('../models/dispositivoModel')
const { ativarLeitorArduino, getDadosArduino } = require('../configs/arduino')

ativarLeitorArduino().then( leitor => {

    console.info('Iniciando escuta do Arduino');

    leitor.on('data', codigoDispositivo => 
                        registrarViagem(codigoDispositivo.substring(1,12)))
    
})

const registrarViagem = async codigoDispositivo => {

    model = new DispositivoModel()

    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    const { Resposta: resposta } = await model.registrarViagem(codigoDispositivo, numeroSerialArduino)
    
    console.log(resposta)
    
    return resposta

}

module.exports = {
    registrarViagem,
}