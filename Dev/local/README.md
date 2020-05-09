# Check Trip

> Api para gerenciar o dispositivo responsável pelo controle de viagens no sistema de transporte público por ônibus em São Paulo (SPTrans).

## Estrutura de arquivos e pastas:

![image](https://user-images.githubusercontent.com/39804819/81461503-b368fe80-9182-11ea-9c56-cf611d445484.png)

### > index.js:
Configuração de servidor express e inicialização da api.

### > endpoints.http:
Arquivo para efetuar testes das rotas presentes na api. Estão comentados no modelo < função >: < retorno esperado >

### > src > routes.js
Todas as rotas que estão presentes na api.

### > src > configs >
Arquivos de configuração ou funções úteis em todo o projeto.

### > src > controllers >
Centralizada todas as validações e regras de negócio.

### > src > models >
Centralizada toda a conexão com o banco de dados.