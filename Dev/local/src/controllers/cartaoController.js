const CartaoModel = require('../models/cartaoModel')
const { alterarChamada } = require('../configs/arduino')

const cadastrar = async (req, res) => {
    model = new CartaoModel()
    alterarChamada(codigoDispositivo => {model.create(codigoDispositivo, res)})
}

const editar = async (req, res) => {
    const { id } = req.params

    model = new CartaoModel()
    
    alterarChamada(codigoDispositivo => {model.update(codigoDispositivo+1, res, id)})
}

const deletar = async (req, res) => {
    const { id } = req.params
    model = new CartaoModel(id)
    
    await model.delete(id)
    
    res.status(200).json("Cartão deletado com sucesso!")
}

module.exports = {
    cadastrar,
    editar,
    deletar
}