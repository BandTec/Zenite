'use strict'

const express = require('express')
const routes = require('./src/routes')
const cors = require('cors')

const server = express()

server.use(express.json())
server.use(cors())
server.use(routes)

server.listen(3333)
console.log("Servidor rodando...")