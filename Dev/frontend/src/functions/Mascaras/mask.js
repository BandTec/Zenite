// função do artigo:
// https://medium.com/trainingcenter/mascara-de-cpf-com-react-javascript-a07719345c93

// cpf -> 000.000.000-09
export const cpfMask = value => {
  return value
    .replace(/\D/g, '') // substitui qualquer caracter que nao seja numero por nada
    .replace(/(\d{3})(\d)/, '$1.$2') // captura 2 grupos de numero o primeiro de 3 e o segundo de 1, apos capturar o primeiro grupo ele adiciona um ponto antes do segundo grupo de numero
    .replace(/(\d{3})(\d)/, '$1.$2')
    .replace(/(\d{3})(\d{1,2})/, '$1-$2')
    .replace(/(-\d{2})\d+?$/, '$1') // captura 2 numeros seguidos de um traço e não deixa ser digitado mais nada
}

// telefone 00000-0000
export const telefoneMask = value => {
  return value
    .replace(/\D/g, '') // substitui qualquer caracter que nao seja numero por nada
    .replace(/(\d{5})(\d)/, '$1-$2') // captura 1 grupo de numero o primeiro de 5 apos capturar ele adiciona um traço no final
    .replace(/(-\d{4})\d+?$/, '$1') // captura 4 numeros e não deixa ser digitado mais nada
}

// data de nascimento 00/00/0000
export const dataMask = value => {
  return value
    .replace(/\D/g, '') // substitui qualquer caracter que nao seja numero por nada
    .replace(/(\d{2})(\d)/, '$1/$2') // captura 2 grupos de numero o primeiro de 2, apos capturar ele adiciona uma barra antes do segundo grupo de numero
    .replace(/(\d{2})(\d)/, '$1/$2') // captura 2 grupos de numero o primeiro de 2, apos capturar ele adiciona uma barra antes do segundo grupo de numero
    .replace(/(\/\d{4})\d+?$/, '$1') // captura 4 numeros e não deixa ser digitado mais nada
}