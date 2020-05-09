const DispositivoModel = require('../models/dispositivoModel')
const { ativarLeitorArduino, getDadosArduino } = require('../configs/arduino')
let leitor

const iniciarEscuta = () => leitor.on('data', codigoDispositivo => 
                            registrarViagem(codigoDispositivo.substring(1,12)))

const iniciar = (req, res) => {
    ativarLeitorArduino().then( leitorAtivado => {
        leitor = leitorAtivado
        iniciarEscuta()
        res.status(202).json("Arduino iniciado...")

    }).catch(error => {
        console.error(error.message)
        res.status(409).json(error.message)
    })
}

const registrarViagem = async codigoDispositivo => {

    model = new DispositivoModel()

    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    const resposta = await model.registrarViagem(codigoDispositivo, numeroSerialArduino)

    return resposta
}

const cadastrarCartao = async () => {
    
    model = new DispositivoModel()
    leitor.on('data', async codigoDispositivo => 
                await model.cadastrarCartao(codigoDispositivo.substring(1,12)))
    setTimeout(() => iniciarEscuta(), 5000)
}

const cadastrar = async (req, res) => {
    model = new DispositivoModel()

    let { dispositivo } = req.params

    switch (dispositivo.toLowerCase()) {
        case "cartao":
            cadastrarCartao()
            res.status(200).json("Aproxime o cartão nos proximos 5 segundos...")
            break

        case "arduino":
            const { serialNumber: numeroSerialArduino } = await getDadosArduino()

            try {
                await model.cadastrarArduino(numeroSerialArduino)
                res.status(200).json("Arduino cadastrado")
    
            } catch (error) {
                res.status(500).json("Ocorreu um erro")
            }
            break
        default:
            res.status(406).json("Dispositivo inválido!")
            break
    }
}

module.exports = {
    registrarViagem,
    cadastrar,
    iniciar
}