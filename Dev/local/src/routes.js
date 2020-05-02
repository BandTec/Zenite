'use strict'

const express = require('express')

const routes = express.Router()

const ArduinoController = require('./controllers/arduinoController')

routes.get('/status', async (req, res) => {
    const respose = await ArduinoController.verificaStatus(req, res)
    return respose
})

module.exports = routes