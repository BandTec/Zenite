'use strict'

const express = require('express')

const routes = express.Router()

const DispositivoController = require('./controllers/dispositivoController')

routes.get('/', (req, res) => res.status(200).json("Hello world!"))

routes.post('/cadastrar/:dispositivo', async (req, res) => 
   await DispositivoController.cadastrar(req, res) 
)

routes.post('/iniciar', async (req, res) => {
    await DispositivoController.iniciar(req, res)
})


module.exports = routes