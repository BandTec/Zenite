## API ZENITE

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

```

### Consulta Todos 
**GET** http://localhost:8080/api/fiscal
-> Requer token

### Consulta por ID
**GET** http://localhost:8080/api/fiscal/{id}
-> Requer token

---

## Rota Administrador

### Consulta Todos 
**GET** http://localhost:8080/api/administrador
-> Requer token

### Consulta por ID
**GET** http://localhost:8080/api/administrador/{id}
-> Requer token

---

## Rota Motorista

### Consulta Todos 
**GET** http://localhost:8080/api/motorista
-> Requer token

---

## Rota PontoFinal

### Consulta Todos 
**GET** http://localhost:8080/api/pontofinal
-> Requer token

---

## Rota Viagem

### Consulta Todos 
**GET** http://localhost:8080/api/viagem
-> Requer token

---

## Rota Linha

### Consulta Todos 
**GET** http://localhost:8080/api/linha
-> Requer token

