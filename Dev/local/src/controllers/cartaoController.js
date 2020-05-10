const CartaoModel = require('../models/cartaoModel')

const cadastrar = async (req, res) => {

    model = new CartaoModel()
    const resposta = await model.create()

    res.status(201).json(resposta)
}

const editar = async (req, res) => {
    const { id } = req.params

    model = new CartaoModel()
    
    const resposta = await model.update(id)

    res.status(200).json(resposta)
}

const deletar = async (req, res) => {
    const { id } = req.params

    model = new CartaoModel()
    
    const resposta = await model.delete(id)

    res.status(200).json(resposta)
}

module.exports = {
    cadastrar,
    editar,
    deletar
}