'use strict'

const express = require('express')

const routes = express.Router()

const DispositivoController = require('./controllers/dispositivoController')
const ArduinoController = require('./controllers/arduinoController')
const CartaoController = require('./controllers/cartaoController')

routes.get('/', (req, res) => res.status(200).json("Hello world!"))

// Arduino
routes.post('/arduino', async (req, res) => 
    await ArduinoController.cadastrar(req, res) 
)

routes.put('/arduino', async (req, res) =>
    await ArduinoController.editar(req, res)    
)

routes.delete('/arduino', async (req, res) =>
    await ArduinoController.deletar(req, res)
)

// Cartão
routes.post('/cartao', async (req, res) => 
    await CartaoController.cadastrar(req, res)
)

routes.put('/cartao', async (req, res) =>
    await CartaoController.editar(req, res)
)

routes.delete('/cartao', async (req, res) =>
    await CartaoController.deletar(req, res)
)

// Dispositivo
routes.post('/iniciar', (req, res) => 
    DispositivoController.iniciar(req, res)
)


module.exports = routes