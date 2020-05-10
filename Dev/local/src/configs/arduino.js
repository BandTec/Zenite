const portaSerial = require('serialport')

const readline = portaSerial.parsers.Readline

const getDadosArduino = async () => {
    const portas = await portaSerial.list()

    const portaArduino = portas.filter(porta =>
        porta.vendorId == 2341 && porta.productId == 43    
    )

    if(portaArduino.length !== 1)
        throw new Error("Nenhum arduino conectado ou mais de um arduino conectado")

    return portaArduino[0]

}

const ativarLeitorArduino = async () => {
    const scanner = new readline()
    const dadosArduino = await getDadosArduino()

    const { path: arduinoCom } = dadosArduino;

    const arduino = new portaSerial(arduinoCom, {
        baudRate:9600
    }, error => {
        if(error) console.error(error.message)
    })

    arduino.pipe(scanner)
    console.log("Arduino conectado na porta %s", arduinoCom)
    
    return scanner

}

module.exports = {
    ativarLeitorArduino,
    getDadosArduino
}