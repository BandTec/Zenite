## API ZENITE


## Rota de Login

**POST** http://localhost:8080/autentica/login
Corpo da Requisição:

```
{
	"password": "senha2",
	"email": "email2"
}
```


## Rotas de TESTE

### Teste de Cadastro do fiscal
*Só cadastra a no momento conta*

**POST** http://localhost:8080/api/fiscal/cadastro

Corpo da Requisição:

```
{
    "senha": "senha6",
    "email": "email6",
    "nivel": 1,
    "nome": "teste",
    "cpf": "123-123-123",
    "dataNascimento": "9879879",
    "numeroTelefone": "23424324",
    "endereco": {
      "id": 0,
      "cep": "234234",
      "logradouro": "rua tal",
      "numero": "12s",
      "complemento": "ola",
      "cidade": "teste",
      "estado": "tt"
    },
    "registroFiscal": "123123",
    "dispositivo": {
      "codigo": "13",
      "tipoDispositivo": 2
    }
}
```
