'use strict'

const express = require('express')

const routes = express.Router()

const DispositivoController = require('./controllers/dispositivoController')

routes.get('/status', async (req, res) => {
    const respose = await DispositivoController.verificaStatus(req, res)
    return respose
})

module.exports = routes