const ArduinoModel = require('../models/arduinoModel')

const adicionar = async (req, res) => {
    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    model = new ArduinoModel(numeroSerialArduino)
    const resposta = await model.create()

    res.status(201).json(resposta)
}

const editar = async (req, res) => {
    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    const { id } = req.params

    model = new ArduinoModel(numeroSerialArduino)
    
    const resposta = await model.update(id)

    res.status(200).json(resposta)
}

const deletar = async (req, res) => {
    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    const { id } = req.params

    model = new ArduinoModel(numeroSerialArduino)
    
    const resposta = await model.delete(id)

    res.status(200).json(resposta)
}

module.exports = {
    adicionar,
    editar,
    deletar
}