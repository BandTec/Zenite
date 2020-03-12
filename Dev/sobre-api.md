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

### Teste de Cadastro só da conta

**POST** http://localhost:8080/autentica/teste-cadastro-conta

Corpo da Requisição:

```
{
      "senha": "senha4",
      "email": "email4",
      "idNivel": 2
}
```

-----

### Teste de consulta de rota protegida com jwt


[GET] http://localhost:8080/autentica/teste-cadastro-conta

Header de Autentificação tipo **Bearer** recebe token gerado na rota de login.


Exemplo de Retorno:

```
{
  "sucess": true,
  "message": "Requisição concluída",
  "value": [
    {
      "id": 1,
      "cep": "1231-09",
      "logradouro": "rua de teste",
      "numero": "12",
      "complemento": "apt 3",
      "cidade": "São Paulo",
      "estado": "SP"
    },
    {
      "id": 2,
      "cep": "1231-09",
      "logradouro": "rua de teste",
      "numero": "12",
      "complemento": "apt 3",
      "cidade": "São Paulo",
      "estado": "SP"
    }
  ]
}
```
