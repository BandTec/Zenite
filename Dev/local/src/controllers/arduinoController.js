const ArduinoModel = require('../models/arduinoModel')
const { getDadosArduino } = require('../configs/arduino')

const cadastrar = async (req, res) => {
    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    model = new ArduinoModel(numeroSerialArduino)
    await model.create()

    res.status(201).json()
}

const editar = async (req, res) => {
    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    const { id } = req.params

    model = new ArduinoModel(numeroSerialArduino)
    await model.update(id)

    res.status(200).json()
}

const deletar = async (req, res) => {
    const { serialNumber: numeroSerialArduino } = await getDadosArduino()
    const { id } = req.params

    model = new ArduinoModel(numeroSerialArduino)
    await model.delete(id)

    res.status(200).json()
}

module.exports = {
    cadastrar,
    editar,
    deletar
}