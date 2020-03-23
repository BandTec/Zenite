## API ZENITE


## Rota de Login

**POST** http://localhost:8080/autentica/login
Corpo da Requisição:

```
{
	"senha": "senha2",
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
 "id": 0,
 "nome": "Fatima Bernades",
 "cpf": "123-123-123",
 "dataNascimento": "1990-09-20",
 "numeroTelefone": "2342 4324",
 "endereco": {
	"id": 5,
	"cep": "1234-43",
	"logradouro": "Rua Maria Antonieta",
	"numero": "12",
	"complemento": "APT 2",
	"cidade": "São Paulo",
	"estado": "SP"
 },
 "registroFiscal": "123123",
 "dispositivo": {
 	"id": 0,
	"codigo": "9080",
	"tipoDispositivo": {
		"id": 1
	}
 },
 "conta": {
	"idConta": 0,
	"senha": "senha6",
	"email": "email6",
 	"nivel": { 
		"id": 3
 	}
 }
}
```
