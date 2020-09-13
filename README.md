# <img width="151" alt="Prancheta 10@2x" src="https://user-images.githubusercontent.com/39804819/76900071-6ef26e00-6877-11ea-9645-a2673dee14b8.png">

[![YARN](https://img.shields.io/badge/Yarn-1.21.1-blue)](https://classic.yarnpkg.com/en/docs/install#windows-stable)    [![JAVA](https://img.shields.io/badge/Java-1.8-red)](https://www.java.com/pt_BR/download/)

**Projeto acadêmico para o 3º e 4º semestre do curso de Análise e Desenvolvimento de Sistemas na Faculdade Bandtec**

O sistema Zênite é um automatizador e gerenciador do processo de fiscalização de transporte urbano realizado pela empresa Órion.
Para ver a primeira versão do projeto feita para o 3º semestre finalizado em Junho de 2020 [clique aqui](https://github.com/BandTec/Zenite/tree/1.0.1).
Nessa segunda fase/versão do projeto refatoramos alguns pontos de nossa aplicação e removemos da nossa arquitetura o dispositio Arduíno e substituimos por um aplicativo realizado em Kotlin. 

- [Site Institucional Órion](https://bandtec.github.io/Zenite/)
- [Aplicação Zenite](https://zeniteapp.azurewebsites.net)
- [Swagger da API Zenite](https://zenitebackend.azurewebsites.net/swagger-ui.html#)

---

## Índice

* Contexto
* Metas - Requisitos
* Diagrama de Arquitetura
* ProtoPersona
* UserStories
* StoryBoard
* Jornada do Usuário
* Banco de Dados
* Diagrama de Arquitetura de Software
* Mockups de Alta Fidelidade
* Equipe

---

## Contexto

Segundo a SPTrans, que gerencia o transporte por ônibus na capital, são feitas 200 mil viagens diárias na cidade. Dessas viagens 2 milhões de viagens não foram cumpridas segundo o órgão fiscalizador TCM(Tribunal de Contas do Município), isso corresponde a 38,6 milhões de km que deixaram de ser operados pelas empresas, somente em dias úteis no período auditado. Essa problemática chamou atenção pois impacta diretamente a população que depende do bom funcionamento deste serviço é crucial para o cotidiano dos moradores da cidade e redondezas.

Partindo deste contexto planejamos nosso sistema de gerenciamento de transporte urbano. Nosso plano é auxiliar no processo de fiscalizar a chegada e saída dos ônibus, onde os principais atores são o fiscal e o motorista; e também auxiliar nas tomadas de decisões dos gestores das linhas com os dados que serão informatizados.

---

## Metas - Requisitos

Na primeira fase do projeto os requisitos do projeto foram os abaixo: 
* [x] Site Institucional
- [x] Aplicação
- [x] IOT Arduíno e Módulo RFID (RC522)
- [x] Hospedagem da aplicação e banco de dados no AZURE

Agora na segunda fase do projeto temos os seguintes requisitos:
  
* [ ] *Backend do projeto no AWS*
  - [ ] Configuração de ambiente de alta disponibilidade e segurança
  - [ ] Configuração de Balanceamento de carga
  - [ ] CD/CI com Jenkins
  - [ ] Stress Tests
* [ ] *Aplicação Web*
  - [ ] Tela para adicionar cronograma da linha
  - [ ] Tela para visualizar cronograma da linha
* [ ] *Aplicativo Android Kotlin*
  - [ ] Aplicativo salvando dados offline
  - [ ] Login e Logout
  - [ ] Esqueci minha senha
  - [ ] Módulo Fiscal
    - [ ] Leitor de QrCode para iniciar e finalizar uma viagem (Lendo o QrCode do Motorista)
      - [ ] Opção de adicionar quantidade de passageiros ao finalizar viagem.
    - [ ] Cronograma diário das viagens
      - [ ] Opção de alterar intervalo de saída dos ônibus (assim alterando o cronograma)
    - [ ] Lista das linhas fiscalizadas pelo fiscal
      - [ ] Lista de ônibus da linha
      - [ ] Lista de motorista da linha
  - [ ] Módulo Motorista
    - [ ] Tela contendo QrCode que identifica o Motorista
    - [ ] Tela com dados da viagem atual / ou próxima viagem, dados do ônibus que esta alocado e fiscal de sua linha
    - [ ] Históricos de viagens (diário / semanal)


---

## Diagrama de Arquitetura

![imagem do diagrama de arquitetura](https://github.com/BandTec/Zenite/blob/master/Documentacao/4Semestre/Sprint1/V1Desenho_Solucao_LLD.png)

---

## ProtoPersona

<div style="display: flex; flex-direction: row;">
<img src="https://github.com/BandTec/Zenite/blob/master/Documentacao/3Semestre/Sprint1/ProtoPersonas/ProtoPersonaFiscal.png" width="40%">


<img src="https://github.com/BandTec/Zenite/blob/master/Documentacao/3Semestre/Sprint1/ProtoPersonas/ProtoPersonaGerente.png" width="40%">
</div>

---

## UserStories

**Fiscal**
> Eu fiscalizo várias linhas e faço anotações nos relatórios de papel dos motoristas com o horário de saída e chegada, assim controlo todos os ônibus

> Preciso tomar decisões baseadas no tempo de espera, números de viagens e quantidade de veículos, todas essas informações mudam diariamente,porém eu tenho que ser cuidadoso e rápido.

**Gerente**
> Preciso ser informado quando há atrasos recorrentes em um trajeto especifico, para relatar a necessidade de aumento na frota

> Necessito determinar padrões de serviço prestado e  mostrar resultados para meus superiores

**Motorista**
> Eu como motorista tenho um horário a cumprir para chegar e sair, mas é difícil em horário de pico, chego atrasado e nunca sei quanto tempo vou demorar para sair


> Preencho um relatório toda viagem, com horário e número da catraca e o fiscal faz anotações nele também 

---

## Banco de Dados

<img src="https://github.com/BandTec/Zenite/blob/master/Documentacao/3Semestre/Sprint1/Banco%20de%20dados/MER.png" width="60%">

---

## Diagrama de Arquitetura de Software

![imagem do diagrama de arquitetura de software](https://github.com/BandTec/Zenite/blob/master/Documentacao/4Semestre/Sprint1/V3Diagrama%20Arquitetura%20de%20Software.png)

---

# Mockups de Alta Fidelidade

Todos os mockups foram feitos com a aplicação web Figma e podem ser vistos na pasta : [`/Documentação/4Semestre/Sprint1/Mockups`](https://github.com/BandTec/Zenite/tree/master/Documentacao/4Semestre/Sprint1/Mockups)

![mockup de alta fidelidade da consulta do fiscal](https://github.com/BandTec/Zenite/blob/master/Documentacao/3Semestre/Sprint2/Mockups%20Alta%20Fidelidade/visualizar-fiscal.png)

---

### Equipe

| [**Alex Buarque**](https://github.com/alexbuarque) | [**Fernanda Esteves**](https://github.com/esteves-esta) | [**João Pedro Soares**](https://github.com/jPedroSoares) | [**Lais Silva**](https://github.com/Laissilvaa) | [**Raissa Arantes**](https://github.com/Rayssawoods) | [**Vitor Silva**](https://github.com/vitorsilv) | [**Fábiola Canedo**](https://github.com/Fabicaneyu) |

