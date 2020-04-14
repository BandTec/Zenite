## API ZENITE

## Rota do Swagger UI
-> http://localhost:8080/swagger-ui.html#/

## Rota de Login

**POST** http://localhost:8080/autentica/login
Corpo da Requisição: 
(abaixo credenciasi do adm)

```
{
 "senha": "12345678",
 "email": "adm@adm.com"
}
```

---

## Rotas Fiscal

### Cadastro 
**POST** http://localhost:8080/api/fiscal
-> Requer token

Corpo da Requisição:

```
{
    "nome": "Carolina Ribeiro Esteves da Silva Machado",
    "cpf": "815.436.148-50",
    "dataNascimento": "1993-09-23",
    "numeroTelefone": "9 1123-4321",
		"senha": "12345678",
		"email": "emailnovissimo@fiscal.com.br",
    "endereco": {
      "cep": "09878023",
      "logradouro": "Rua Antonieta",
      "numero": "78",
      "complemento": "APT 1",
      "cidade": "São Paulo",
      "estado": "SP"
    },	
    "registroFiscal": "12.123.123",
    "dispositivo": {
      "codigo": "542-345",
      "tipoDispositivo": {
        "id": 2
      }
    }
}
```

### Edição 
**PUT** http://localhost:8080/api/fiscal
-> Requer token

Corpo da Requisição:

```
{
  "nome": "Carolina Ribeiro Esteves da Silva Machado",
  "cpf": "815.436.148-50",
  "dataNascimento": "1993-09-23",
  "numeroTelefone": "9 1123-4321",
  "endereco": {
    "cep": "09878023",
    "logradouro": "Rua Antonieta",
    "numero": "78",
    "complemento": "APT 1",
    "cidade": "São Paulo",
    "estado": "SP"
  },
  "registroFiscal": "12.123.123",
  "dispositivo": {
    "codigo": "542-345",
    "tipoDispositivo": {
      "id": 1
    }
  },
  "conta": {
    "senha": "senha213",
    "email": "emailnovo@fiscal.com.br",
    "nivel": {
      "id": 3
    }
  }
}
```

### Consulta Todos 
**GET** http://localhost:8080/api/fiscal
-> Requer token

### Consulta por ID
**GET** http://localhost:8080/api/fiscal/{id}
-> Requer token

### Deletar por ID
**DELETE** http://localhost:8080/api/fiscal/{id}
-> Requer token


### Alterar 
**PUT** http://localhost:8080/api/fiscal
```
{
  "id": 4,
  "nome": "Carolina Ribeiro da Silva Machado",
  "cpf": "815.436.148-50",
  "dataNascimento": "1993-09-23",
  "numeroTelefone": "9 1123-4321",
  "endereco": {
    "id": 6,
    "cep": "09878023",
    "logradouro": "Rua Antonieta",
    "numero": "78",
    "complemento": "APT 1",
    "cidade": "São Paulo",
    "estado": "SP"
  },
  "registroFiscal": "12.123.123",
  "dispositivo": {
    "id": 7,
    "codigo": "542-345",
    "tipoDispositivo": {
      "id": 1
    }
  },
  "conta": {
    "idConta": 38,
    "senha": "mudandosenha",
    "email": "mudandoemail@fiscal.com.br",
    "nivel": {
      "id": 3
    }
  }
}
```
---

## Rota Administrador

### Consulta Todos 
**GET** http://localhost:8080/api/administrador
-> Requer token

### Consulta por ID
**GET** http://localhost:8080/api/administrador/{id}
-> Requer token

### Inserir 
**POST** http://localhost:8080/api/administrador
```
{
    "nome": "admin",
    "conta": {
      "senha": "senha",
      "email": "maisum@admin.com",
      "nivel": {
        "id": 1
      }
    }
}
```

### Alterar 
**PUT** http://localhost:8080/api/administrador
```
{
  "id": 20,
  "nome": "vitoriana",
  "conta": {
    "idConta": 42,
    "senha": "senha1",
    "email": "vitoriana@admin.com",
    "nivel": {
      "id": 1
    }
  }
}
```

---

## Rota Motorista

### Consulta Todos 
**GET** http://localhost:8080/api/motorista
-> Requer token

----------------------------------------------

## Rota PontoFinal

### Consulta Todos 
**GET** http://localhost:8080/api/pontofinal

### Inserir 
**Post**
localhost:8080/api/pontofinal/
{
        "id": 6,
        "nome": "Vila Iorio"
    },

### Alterar 
**Put**
localhost:8080/api/pontofinal/
{
        "id": 6,
        "nome": "Vila Maria"
    },
--------------------------------------

## Rota Viagem

### Consulta Todos 
**GET** http://localhost:8080/api/viagem

### Inserir 
***Post**
localhost:8080/api/viagem/


### Alterar
***Put**
localhost:8080/api/viagem/

--------------------------------------------

## Rota Carro

###Consulta Todos
*** GET** http://localhost:8080/api/onibus

### Inserir 
***Post**
localhost:8080/api/onibus/
{
        "id": 6,
        "numero": "123456",
        "dispositivo": {
            "id": 2,
            "codigo": "542345",
            "tipoDispositivo": {
                "id": 2,
                "descricao": "ARDUINO"
            }
           
        }
        
}

### Alterar
***Put**
localhost:8080/api/onibus/

{
        "id": 6,
        "numero": "123333",
        "dispositivo": {
            "id": 2,
            "codigo": "542345",
            "tipoDispositivo": {
                "id": 2,
                "descricao": "ARDUINO"
            }
           
        }
        
}

------------------------------------------------
## Rota Linha

### Consulta Todos 
**GET** http://localhost:8080/api/linha
-> Requer token

