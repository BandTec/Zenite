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

// data de nascimento 01/08/1990
export const dataMask = (value) => {
  return value
    .replace(/\D/g, "") // substitui qualquer caracter que nao seja numero por nada
    .replace(/(\d{2})(\d)/, "$1/$2") // captura 2 grupos de numero o primeiro de 2, apos capturar ele adiciona uma barra antes do segundo grupo de numero
    .replace(/(\d{2})(\d)/, "$1/$2") // captura 2 grupos de numero o primeiro de 2, apos capturar ele adiciona uma barra antes do segundo grupo de numero
    .replace(/(\/\d{4})\d+?$/, "$1"); // captura 4 numeros e não deixa ser digitado mais nada
};

// data de nascimento entra como 1990-08-01 sai como 01/08/1990
export const reformatarData = (value) => {
  return value.replace(/(\d{4})-(\d{2})-(\d{2})/g, "$3/$2/$1");
};

// data de nascimento entra como 01/08/1990 sai como 1990-08-01
export const formatarData = (value) => {
  return value.replace(/(\d{2})\/(\d{2})\/(\d{4})/g, "$3-$2-$1");
};


// cep 00000-000
export const cepMask = value => {
  return value
    .replace(/\D/g, '') // substitui qualquer caracter que nao seja numero por nada
    .replace(/(\d{5})(\d)/, '$1-$2') // captura 2 grupos de numero o primeiro de 2, apos capturar ele adiciona uma barra antes do segundo grupo de numero
    .replace(/(-\d{3})\d+?$/, '$1') // captura 4 numeros e não deixa ser digitado mais nada
}