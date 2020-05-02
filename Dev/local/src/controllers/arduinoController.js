const DispositivoModel = require('../models/dispositivoModel')
const { ativarLeitorArduino, numeroSerialArduino } = require('../configs/arduino')

ativarLeitorArduino().then( leitor => {

    console.info('Iniciando escuta do Arduino');

    leitor.on('data', codigoDispositivo => 
                        registrarViagem(codigoDispositivo.substring(1,12)))
    
})

const registrarViagem = async codigoDispositivo => {

    model = new DispositivoModel()
    
    const resposta = await model.registrarViagem(codigoDispositivo, numeroSerialArduino)
    if(resposta.length){
        console.log("Dispositivo registrado")
    }else{
        console.log("Dispositivo não registrado")
    }
    return resposta

}

module.exports = {
    registrarViagem,
}