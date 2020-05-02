const portaSerial = require('serialport')

const readline = portaSerial.parsers.Readline
let numeroSerialArduino;

const getDadosArduino = async () => {
    const portas = await portaSerial.list()

    const portaArduino = portas.filter(porta =>
        porta.vendorId == 2341 && porta.productId == 43    
    )

    if(portaArduino.length !== 1)
        throw new Error("Nenhum arduino conectado ou mais de um arduino conectado")
    
    console.log("Arduino conectado na porta %s", portaArduino[0].path)

    return portaArduino[0]

}

const ativarLeitorArduino = async () => {
    const scanner = new readline()
    const dadosArduino = await getDadosArduino()

    const { path: arduinoCom, serialNumber } = dadosArduino;
    numeroSerialArduino = serialNumber;

    const arduino = new portaSerial(arduinoCom, {
        baudRate:9600
    })
    
    arduino.pipe(scanner)

    return scanner
}

module.exports = {
    ativarLeitorArduino,
    getDadosArduino
}