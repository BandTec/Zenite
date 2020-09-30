--------------------------------------------------------------------INICIO TABELAS--------------------------------------------------------------------
DROP TABLE IF EXISTS tbl_Nivel
GO
CREATE TABLE tblNivel (
	id_Nivel int primary key IDENTITY,
	descricao varchar(100) not null
)

DROP TABLE IF EXISTS tbl_Conta
GO
CREATE TABLE tbl_Conta (
	id_Conta int primary key identity,
	email varchar(60) not null,
	senha varchar(255) not null,
	fk_Nivel int foreign key references tbl_Nivel(id_Nivel)
)

--DROP TABLE IF EXISTS tbl_Tipo_Dispositivo
--GO
--CREATE TABLE tbl_Tipo_Dispositivo(
--	id_Tipo_Dispositivo int primary key IDENTITY,
--	descricao varchar(10) not null
--)

--DROP TABLE IF EXISTS tbl_Dispositivo
--GO
--CREATE TABLE tbl_Dispositivo (
--	id_Dispositivo int primary key IDENTITY,
--	codigo_Dispositivo varchar(20) not null,
--	fk_Tipo int foreign key references tbl_Tipo_Dispositivo(id_Tipo_Dispositivo)
--)

--DROP TABLE IF EXISTS tblCargo
--GO
--CREATE TABLE tblCargo(
--	idCargo int primary key IDENTITY,
--	descricao varchar(20)
--)

--DROP TABLE IF EXISTS tblTelefone
--GO
--CREATE TABLE tblTelefone (
--	idTelefone int PRIMARY KEY IDENTITY,
--	numero varchar(12) not null
--)

DROP TABLE IF EXISTS tbl_Endereco
GO
CREATE TABLE tbl_Endereco (
	id_Endereco int primary key IDENTITY,
	CEP varchar(8) not null,
	logradouro varchar(120) not null,
	numero varchar(16) not null,
	complemento varchar(60),
	cidade varchar(40),
	estado varchar(2)
)

DROP TABLE IF EXISTS tbl_Admin
GO
CREATE TABLE tbl_Admin(
	id_Admin int primary key IDENTITY,
	nome varchar(100) not null,
	fk_Conta int foreign key references tbl_Conta(id_Conta)
)

DROP TABLE IF EXISTS tbl_Motorista
GO
CREATE TABLE tbl_Motorista(
	id_Motorista int primary key IDENTITY,
	nome_Motorista varchar(100) not null,
	cpf varchar(14) not null unique,
	cnh varchar(11) not null unique,
	dt_Nasc date not null,
	telefone varchar(11) not null,
	--fkRFID int foreign key references tblDispositivo(idDispositivo),
	fk_Conta int foreign key references tbl_Conta(id_Conta),
	fk_Endereco int foreign key references tbl_Endereco(id_Endereco)
)

DROP TABLE IF EXISTS tbl_Fiscal
GO
CREATE TABLE tbl_Fiscal(
	id_Fiscal int primary key IDENTITY,
	nome_Fiscal varchar(100) not null,
	cpf varchar(14) not null unique,
	registro_Fiscal varchar(20) not null unique,
	dt_Nasc date not null,
	telefone varchar(11) not null,
	fk_Conta int foreign key references tbl_Conta(id_Conta),
	fk_Endereco int foreign key references tbl_Endereco(id_Endereco),
	--fkDispositivo int foreign key references tblDispositivo(idDispositivo)
)

DROP TABLE IF EXISTS tbl_Gerente
GO
CREATE TABLE tbl_Gerente(
	id_Gerente int primary key IDENTITY,
	nome_Gerente varchar(100) not null,
	cpf varchar(14) not null unique,
	dt_Nasc date not null,
	telefone varchar(11) not null,
	fk_Conta int foreign key references tbl_Conta(id_Conta),
	fk_Endereco int foreign key references tbl_Endereco(id_Endereco)
)

--N�o estamos mais de um telefone mais caso isso seja preciso essa ser� a estrutura da tabela de associa��o
--DROP TABLE IF EXISTS tblTelefone_Funcionario
--GO
--CREATE TABLE tblTelefone_Funcionario(
--	idTelefone int foreign key references tblTelefone(idTelefone),
--	idFuncionario int not null,
--	cargoFunc int not null
--)

DROP TABLE IF EXISTS tbl_Ponto_Final
GO
CREATE TABLE tbl_Ponto_Final (
	id_Ponto_Final int primary key IDENTITY,
	nome_Terminal varchar(80) not null
)

DROP TABLE IF EXISTS tbl_Linha
GO
CREATE TABLE tbl_Linha (
	id_Linha int primary key IDENTITY,
	numero_Linha varchar(7) not null,
	fk_Ponto_Ida int foreign key REFERENCES tbl_Ponto_Final(id_Ponto_Final),
	fk_Ponto_Volta int foreign key REFERENCES tbl_Ponto_Final(id_Ponto_Final)
)

DROP TABLE IF EXISTS tbl_Carro
GO
CREATE TABLE tbl_Carro(
	id_Carro int primary key IDENTITY,
	numero_Carro varchar(10) not null unique,
	--fk_Dispositivo int foreign key references tbl_Dispositivo(idDispositivo)	
)

DROP TABLE IF EXISTS tbl_Motorista_Carro
GO
CREATE TABLE tbl_Motorista_Carro(
	id_Motorista int foreign key references tbl_motorista(id_Motorista),
	id_Carro int foreign key references tbl_carro(id_Carro),
	primary key(id_Motorista, id_Carro)
)

DROP TABLE IF EXISTS tbl_Carro_Linha
GO
CREATE TABLE tbl_Carro_Linha(
	id_Carro int foreign key references tbl_Carro(id_Carro),
	id_Linha int foreign key references tbl_Linha(id_Linha),
	primary key(id_Carro, id_Linha)
)

DROP TABLE IF EXISTS tbl_fiscal_linha
GO
CREATE TABLE tbl_fiscal_linha(
	id_fiscal int foreign key references tbl_fiscal(id_fiscal),
	id_Linha int foreign key references tbl_Linha(id_Linha),
	primary key(id_fiscal, id_Linha)
)

DROP TABLE IF EXISTS tbl_Dados_Viagem
GO
CREATE TABLE tbl_Dados_Viagem(
	id_Dados_Viagem int primary key IDENTITY(1000,1),
	id_Carro int foreign key references tbl_Carro(id_Carro),
	id_Linha int foreign key references tbl_Linha(id_Linha),
	id_Motorista int foreign key references tbl_Motorista(id_Motorista),
	id_Fiscal int foreign key references tbl_Fiscal(id_Fiscal),
	hora_Saida datetime not null,
	hora_Chegada datetime,
	qtd_Passageiros int
)

DROP TABLE IF EXISTS tbl_cronograma
GO
CREATE TABLE tbl_cronograma(
	id_cronograma int primary key IDENTITY,
	--fk_motorista int foreign key references tbl_motorista(id_motorista),
	--fk_linha int foreign key references tbl_linha(id_linha),
	--hora_prevista_saida datetime,
	--hora_prevista_chegada datetime,
	data_cronograma date not null,
	fk_fiscal int foreign key references tbl_fiscal(id_fiscal),
	--viagem_status int -- 1 = aguardando, 2 = andamento, 3 = finalizada
)

DROP TABLE IF EXISTS tbl_cronograma_horarios
GO
CREATE TABLE tbl_cronograma_horarios(
	id_cronograma_horarios int primary key IDENTITY,
	fk_motorista int foreign key references tbl_motorista(id_motorista),
	fk_carro int foreign key references tbl_carro(id_carro),
	fk_linha int foreign key references tbl_linha(id_linha),
	hora_prevista_saida datetime,
	hora_prevista_chegada datetime,
	viagem_status int default 1, -- 1 = aguardando, 2 = andamento, 3 = finalizada
	fk_cronograma int foreign key references tbl_cronograma(id_cronograma)
)

DROP TABLE IF EXISTS tbl_cronograma_horarios_alterados
GO
CREATE TABLE tbl_cronograma_horarios_alterados(
	id_cronograma_horarios_alterados int primary key IDENTITY,
	nova_hora_prevista_saida datetime,
	nova_hora_prevista_chegada datetime,
	fk_cronograma_horarios int foreign key references tbl_cronograma_horarios(id_cronograma_horarios)
)

---Pensar na tabela de organiza��o por arduino/rfid/dados viagem....

--------------------------------------------------------------------FIM TABELAS--------------------------------------------------------------------

--------------------------------------------------------------------INICIO DADOS MANUAIS--------------------------------------------------------------------

INSERT INTO tblNivel values 
('Admin'),
('Gerente'),
('Fiscal'),
('Motorista'),
('Passageiro');

select * from tblNivel


--------------------------------------------------------------------FIM DADOS MANUAIS--------------------------------------------------------------------

--------------------------------------------------------------------INICIO PROCEDURES--------------------------------------------------------------
DROP PROCEDURE IF EXISTS spCadastroAdmin_i
GO
CREATE PROCEDURE spCadastroAdmin_i(
	@email varchar(60),
	@senha varchar(255),
	@nome varchar(100)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA CADASTRO DE ADMINS
||	DATA: 07/03/2020
||	ATUALIZA��ES: CRIA��O (07/03/2020)
||	
||	EXEMPLO: EXEC spCadastroAdmin_i @email = 'alexbuarque20@gmail.com', @senha = '123456789', @nome = 'Alex Buarque da Silva Gusm�o'
*/
---------------------------------------------------------

AS
BEGIN

	DECLARE @ID_CONTA INT

	INSERT INTO tblConta values (
		@email, @senha, 1
	)

	SELECT @ID_CONTA = @@IDENTITY

	INSERT INTO tblAdmin values (
		@nome, @ID_CONTA
	)

END



DROP PROCEDURE IF EXISTS spLogin
GO
CREATE PROCEDURE spLogin(
	@email varchar(60),
	@senha varchar(255)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA LOGIN DE USUARIO
||	DATA: 07/03/2020
||	ATUALIZA��ES: CRIA��O (07/03/2020)
||	
||	EXEMPLO: EXEC spLogin @email = 'alexbuarque20@gmail.com', @senha = '123456789'
*/
---------------------------------------------------------

AS
BEGIN

	SELECT * FROM tblConta 
	WHERE email = @email and senha = @senha

END


DROP PROCEDURE IF EXISTS spNivel_s
GO
CREATE PROCEDURE spNivel_s(
	@idNivel int = null,
	@descricao varchar(100) = null
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCA DOS NIVEIS DE USU�RIOS DO SISTEMA
||	DATA: 07/03/2020
||	ATUALIZA��ES: CRIA��O (07/03/2020)
||	
||	EXEMPLO: EXEC spNivel_s @idNivel = null, @descricao = null
*/
---------------------------------------------------------

AS
BEGIN
	
	if(@idNivel is not null or @descricao is not null)
	BEGIN
		SELECT * FROM tblNivel WHERE idNivel = @idNivel or descricao = @descricao
	END
	ELSE
	BEGIN
		SELECT * FROM tblNivel
	END
	
END

DROP PROCEDURE IF EXISTS spLinha_BuscaPorId
GO
CREATE PROCEDURE spLinha_BuscaPorId(
	@idLinha int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCA DOS DADOS DE UMA LINHA ESPECIFICA
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_BuscaPorId @idLinha = 1
*/
---------------------------------------------------------

AS
BEGIN
	
	SELECT
		linha.idLinha, linha.numeroLinha AS numeroLinha, pontoIda.idPontoFinal AS idPontoIda,
		pontoIda.nomeTerminal AS terminalIda, pontoVolta.idPontoFinal, pontoVolta.nomeTerminal AS terminalVolta
	FROM tblLinha as linha
		INNER JOIN tblPontoFinal as pontoIda on linha.fkPontoIda = pontoIda.idPontoFinal
		INNER JOIN tblPontoFinal as pontoVolta on linha.fkPontoVolta = pontoVolta.idPontoFinal
	WHERE linha.idLinha = @idLinha

END

DROP PROCEDURE IF EXISTS spLinha_BuscaTodos
GO
CREATE PROCEDURE spLinha_BuscaTodos
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCA DOS DADOS DE TODAS AS LINHAS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_BuscaTodos
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT
		linha.idLinha, linha.numeroLinha AS numeroLinha, pontoIda.idPontoFinal AS idPontoIda,
		pontoIda.nomeTerminal AS terminalIda, pontoVolta.idPontoFinal, pontoVolta.nomeTerminal AS terminalVolta
	FROM tblLinha as linha
		INNER JOIN tblPontoFinal as pontoIda on linha.fkPontoIda = pontoIda.idPontoFinal
		INNER JOIN tblPontoFinal as pontoVolta on linha.fkPontoVolta = pontoVolta.idPontoFinal

END

DROP PROCEDURE IF EXISTS spLinha_BuscaTodosPorPontoVolta
GO
CREATE PROCEDURE spLinha_BuscaTodosPorPontoVolta(
	@idPontoVolta int = null,
	@pontoVolta varchar(80) = null
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCA DOS DADOS DE TODAS AS LINHAS QUE TEM UM PONTO VOLTA EM COMUM
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_BuscaTodosPorPontoVolta @idPontoVolta = 1, @pontoVolta = null
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT
		linha.idLinha, linha.numeroLinha AS numeroLinha, pontoIda.idPontoFinal AS idPontoIda,
		pontoIda.nomeTerminal AS terminalIda, pontoVolta.idPontoFinal, pontoVolta.nomeTerminal AS terminalVolta
	FROM tblLinha as linha
		INNER JOIN tblPontoFinal as pontoIda on linha.fkPontoIda = pontoIda.idPontoFinal
		INNER JOIN tblPontoFinal as pontoVolta on linha.fkPontoVolta = pontoVolta.idPontoFinal
	WHERE pontoVolta.idPontoFinal = @idPontoVolta OR pontoVolta.nomeTerminal = @pontoVolta

END


DROP PROCEDURE IF EXISTS spLinha_BuscaTodosPorPontoIda
GO
CREATE PROCEDURE spLinha_BuscaTodosPorPontoIda(
	@idPontoIda int = null,
	@pontoIda varchar(80) = null
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCA DOS DADOS DE TODAS AS LINHAS QUE TEM UM PONTO IDA EM COMUM
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_BuscaTodosPorPontoIda @idPontoIda = 1, @pontoIda = null
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT
		linha.idLinha, linha.numeroLinha AS numeroLinha, pontoIda.idPontoFinal AS idPontoIda,
		pontoIda.nomeTerminal AS terminalIda, pontoVolta.idPontoFinal, pontoVolta.nomeTerminal AS terminalVolta
	FROM tblLinha as linha
		INNER JOIN tblPontoFinal as pontoIda on linha.fkPontoIda = pontoIda.idPontoFinal
		INNER JOIN tblPontoFinal as pontoVolta on linha.fkPontoVolta = pontoVolta.idPontoFinal
	WHERE pontoVolta.idPontoFinal = @idPontoIda OR pontoVolta.nomeTerminal = @pontoIda

END


DROP PROCEDURE IF EXISTS spLinha_BuscaUltimoId
GO
CREATE PROCEDURE spLinha_BuscaUltimoId
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCA DOS DADOS DA ULTIMA LINHA ADICIONADA
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_BuscaUltimoId @idPontoIda = 1, @pontoIda = null
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT MAX(idLinha) as idLinha from tblLinha

END


DROP PROCEDURE IF EXISTS spLinha_Inserir
GO
CREATE PROCEDURE spLinha_Inserir(
	@numeroLinha varchar(7),
	@fkPontoIda int,
	@fkPontoVolta int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA INSERIR UMA NOVA LINHA DE �NIBUS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_Inserir @numeroLinha = 2341-12, @fkPontoIda = 47, @fkPontoVolta = 12
*/
---------------------------------------------------------
AS
BEGIN
	
	INSERT INTO tblLinha values (
		@numeroLinha, @fkPontoIda, @fkPontoVolta
	)

END

DROP PROCEDURE IF EXISTS spLinha_Alterar
GO
CREATE PROCEDURE spLinha_Alterar(
	@numeroLinha varchar(7),
	@fkPontoIda int,
	@fkPontoVolta int,
	@idLinha int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA ALTERAR UMA LINHA DE �NIBUS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_Alterar @numeroLinha = 2341-12, @fkPontoIda = 47, @fkPontoVolta = 12, @idLinha = 35
*/
---------------------------------------------------------
AS
BEGIN
	
	UPDATE tblLinha SET
		numeroLinha = @numeroLinha,
		fkPontoIda = @fkPontoIda,
		fkPontoVolta = @fkPontoVolta
	WHERE idLinha = @idLinha

END


DROP PROCEDURE IF EXISTS spLinha_Deletar
GO
CREATE PROCEDURE spLinha_Deletar(
	@idLinha int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA DELETAR UMA LINHA DE �NIBUS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spLinha_Deletar @idLinha = 35
*/
---------------------------------------------------------
AS
BEGIN
	
	DELETE FROM tblLinha WHERE idLinha = @idLinha

END


DROP PROCEDURE IF EXISTS spEndereco_BuscaPorId
GO
CREATE PROCEDURE spEndereco_BuscaPorId(
	@idEndereco int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR UM ENDERE�O ESPECIFICO PELO ID
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_BuscaPorId @idEndereco = 2
*/
---------------------------------------------------------
AS
BEGIN

	SELECT * FROM tblEndereco
	WHERE idEndereco = @idEndereco

END


DROP PROCEDURE IF EXISTS spEndereco_BuscaPorCep
GO
CREATE PROCEDURE spEndereco_BuscaPorCep(
	@cep VARCHAR(8)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR UM ENDERE�O ESPECIFICO PELO CEP
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_BuscaPorCep @CEP = '08140060'
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT * FROM tblEndereco
	WHERE CEP = @cep

END


DROP PROCEDURE IF EXISTS spEndereco_BuscaTodosEnderecos
GO
CREATE PROCEDURE spEndereco_BuscaTodosEnderecos
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR TODOS OS ENDERE�OS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_BuscaTodosEnderecos
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT * FROM tblEndereco

END


DROP PROCEDURE IF EXISTS spEndereco_BuscaUltimoId
GO
CREATE PROCEDURE spEndereco_BuscaUltimoId
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR O ULTIMO ENDERE�O ADICIONADO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_BuscaUltimoId
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT MAX(idEndereco) AS idEndereco from tblEndereco

END



DROP PROCEDURE IF EXISTS spEndereco_Inserir
GO
CREATE PROCEDURE spEndereco_Inserir(
	@CEP varchar(8),
	@logradouro varchar(120),
	@numero varchar(16) ,
	@complemento varchar(60),
	@cidade varchar(40),
	@estado varchar(2)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA INSERIR UM NOVO ENDERECO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_Inserir @cep = '08140060', @logradouro = 'Rua 2', @numero = '178', @complemento = null, @cidade = 'Guarulhos', @estado = 'SP'
*/
---------------------------------------------------------
AS
BEGIN
	
	INSERT INTO tblEndereco values (
		@CEP, @logradouro, @numero, @complemento, @cidade, @estado
	)

END



DROP PROCEDURE IF EXISTS spEndereco_Alterar
GO
CREATE PROCEDURE spEndereco_Alterar(
	@CEP varchar(8),
	@logradouro varchar(120),
	@numero varchar(16) ,
	@complemento varchar(60) = null,
	@cidade varchar(40),
	@estado varchar(2),
	@idEndereco int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA ALTERAR UM ENDERECO EXISTENTE
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_Alterar @cep = '08140060', @logradouro = 'Rua 2', @numero = '178', @complemento = null, @cidade = 'Guarulhos', @estado = 'SP', @idEndereco = 4
*/
---------------------------------------------------------
AS
BEGIN
	
	UPDATE tblEndereco SET
		CEP = @CEP,
		logradouro = @logradouro,
		numero = @numero,
		complemento = @complemento,
		cidade = @cidade,
		estado = @estado
	WHERE idEndereco = @idEndereco

END



DROP PROCEDURE IF EXISTS spEndereco_Deletar
GO
CREATE PROCEDURE spEndereco_Deletar(
	@idEndereco int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA DELETAR UM ENDERE�O EXISTENTE
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spEndereco_Deletar @idEndereco = 4
*/
---------------------------------------------------------
AS
BEGIN
	
	DELETE FROM tblEndereco
	WHERE idEndereco = @idEndereco

END



DROP PROCEDURE IF EXISTS spConta_BuscaPorId
GO
CREATE PROCEDURE spConta_BuscaPorId(
	@idConta int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR UMA CONTA ESPECIFICA PELO ID
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_BuscaPorId @idConta = 110
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT c.*, n.descricao from tblConta c
		INNER JOIN tblNivel n on c.fkNivel = n.idNivel
	WHERE C.idConta = @idConta

END



DROP PROCEDURE IF EXISTS spConta_BuscarPorEmail
GO
CREATE PROCEDURE spConta_BuscarPorEmail(
	@email varchar(60)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR O NIVEL DE UMA CONTA ESPECIFICA PELO E-MAIL
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_buscarNivelPorEmail @email = 'emailteste@orion.com'
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT c.*, n.descricao from tblConta c
		INNER JOIN tblNivel n on c.fkNivel = n.idNivel
	WHERE c.email = @email

END



DROP PROCEDURE IF EXISTS spConta_BuscaTodos
GO
CREATE PROCEDURE spConta_BuscaTodos
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR TODAS AS CONTAS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_BuscaTodos
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT c.*, n.descricao from tblConta c
		INNER JOIN tblNivel n on c.fkNivel = n.idNivel


END


DROP PROCEDURE IF EXISTS spConta_BuscaUltimoId
GO
CREATE PROCEDURE spConta_BuscaUltimoId()
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR O ID DA ULTIMA CONTA ADICIONADA
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_BuscaUltimoId @idConta = 74
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT MAX(idConta) as idConta from tblConta

END



DROP PROCEDURE IF EXISTS spConta_Inserir
GO
CREATE PROCEDURE spConta_Inserir(
	@email varchar(60),
	@senha varchar(255),
	@fkNivel int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA INSERIR UMA NOVA CONTA
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_Inserir @email = 'fulano.ciclano@beltrano.com', @senha = '51656154158696153aa', @fkNivel = 3
*/
---------------------------------------------------------
AS
BEGIN
	
	INSERT INTO tblConta values (
		@email, @senha, @fkNivel
	)

END



DROP PROCEDURE IF EXISTS spConta_Alterar
GO
CREATE PROCEDURE spConta_Alterar(
	@email varchar(60),
	@senha varchar(255),
	@fkNivel int,
	@idConta int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA INSERIR UMA NOVA CONTA
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_Alterar @email = 'fulano.ciclano@beltrano.com', @senha = '51656154158696153aa', @fkNivel = 3, @idConta = 2
*/
---------------------------------------------------------
AS
BEGIN
	
	UPDATE tblConta SET
		email = @email,
		senha = @senha,
		fkNivel = @fkNivel
	WHERE idConta = @idConta

END



DROP PROCEDURE IF EXISTS spConta_Deletar
GO
CREATE PROCEDURE spConta_Deletar(
	@idConta int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA DELETAR UMA CONTA
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spConta_Deletar @idConta = 2
*/
---------------------------------------------------------
AS
BEGIN
	
	DELETE FROM tblConta
	WHERE idConta = @idConta

END



DROP PROCEDURE IF EXISTS spCarro_BuscaPorId
GO
CREATE PROCEDURE spCarro_BuscaPorId(
	@idCarro int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR UM CARRO PELO ID ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_BuscaPorId @idCarro = 87
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT carro.idCarro, carro.numeroCarro,
		   d.idDispositivo, d.codigoDispositivo, d.fkTipo
	FROM tblCarro as carro
		INNER JOIN tblDispositivo d ON carro.fkDispositivo = d.idDispositivo
	WHERE carro.idCarro = @idCarro

END



DROP PROCEDURE IF EXISTS spCarro_BuscarPorNumeroCarro
GO
CREATE PROCEDURE spCarro_BuscaPorNumeroCarro(
	@numeroCarro varchar(10)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR UM CARRO PELO NUMERO ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_BuscaPorNumeroCarro @numeroCarro = 1784621
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT carro.idCarro, carro.numeroCarro, 
		   d.idDispositivo, d.codigoDispositivo, d.fkTipo
	FROM tblCarro as carro
		INNER JOIN tblDispositivo d ON carro.fkDispositivo = d.idDispositivo
	WHERE carro.numeroCarro = @numeroCarro

END



DROP PROCEDURE IF EXISTS spCarro_BuscarTodos
GO
CREATE PROCEDURE spCarro_BuscarTodos
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR TODOS OS CARROS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_BuscarTodos
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT carro.idCarro, carro.numeroCarro,
		   d.idDispositivo, d.codigoDispositivo, d.fkTipo
	FROM tblCarro as carro
		INNER JOIN tblDispositivo d ON carro.fkDispositivo = d.idDispositivo

END



DROP PROCEDURE IF EXISTS spCarro_BuscaUltimoId
GO
CREATE PROCEDURE spCarro_BuscaUltimoId
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR TODOS OS CARROS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_BuscaUltimoId
*/
---------------------------------------------------------
AS
BEGIN
	
	SELECT MAX(idCarro) AS idCarro from tblCarro

END



DROP PROCEDURE IF EXISTS spCarro_Inserir
GO
CREATE PROCEDURE spCarro_Inserir(
	@numeroCarro varchar(10),
	@fkDispositivo int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA INSERIR UM ONIBUS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_Inserir @numeroCarro = '14785236', @fkDispositivo = '3'
*/
---------------------------------------------------------
AS
BEGIN
	
	INSERT INTO tblCarro values (
		@numeroCarro, @fkDispositivo
	)

END



DROP PROCEDURE IF EXISTS spCarro_Alterar
GO
CREATE PROCEDURE spCarro_Alterar(
	@numeroCarro varchar(10),
	@fkDispositivo int,
	@idCarro int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA ALTERAR UM ONIBUS ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_Alterar @numeroCarro = '14785236', @fkDispositivo = '3', @idCarro = 745
*/
---------------------------------------------------------
AS
BEGIN
	
	UPDATE tblCarro SET
		numeroCarro = @numeroCarro,
		fkDispositivo = @fkDispositivo
	WHERE idCarro = @idCarro

END



DROP PROCEDURE IF EXISTS spCarro_Deletar
GO
CREATE PROCEDURE spCarro_Deletar(
	@idCarro int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA DELETAR UM ONIBUS ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spCarro_Deletar @idCarro = 745
*/
---------------------------------------------------------
AS
BEGIN
	
	DELETE FROM tblCarro
	WHERE idCarro = @idCarro

END



DROP PROCEDURE IF EXISTS spPontoFinal_Inserir
GO
CREATE PROCEDURE spPontoFinal_Inserir(
	@nomeTerminal varchar(80)
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA INSERIR UM PONTO FINAL/TERMINAL
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spPontoFinal_Inserir @nomeTerminal = 'Jd. Camargo Novo'
*/
---------------------------------------------------------
AS
BEGIN

	INSERT INTO tblPontoFinal values (
		@nomeTerminal
	)

END



DROP PROCEDURE IF EXISTS spPontoFinal_Alterar
GO
CREATE PROCEDURE spPontoFinal_Alterar(
	@nomeTerminal varchar(80),
	@idPontoFinal int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA ALTERAR UM PONTO FINAL/TERMINAL ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spPontoFinal_Alterar @nomeTerminal = 'Jd. Camargo Velho', @idPontoFinal = 4563
*/
---------------------------------------------------------
AS
BEGIN

	UPDATE tblPontoFinal SET
		nomeTerminal = @nomeTerminal
	WHERE idPontoFinal = @idPontoFinal

END



DROP PROCEDURE IF EXISTS spPontoFinal_Deletar
GO
CREATE PROCEDURE spPontoFinal_Deletar(
	@idPontoFinal int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA DELETAR UM PONTO FINAL/TERMINAL ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spPontoFinal_Alterar @idPontoFinal = 4563
*/
---------------------------------------------------------
AS
BEGIN

	DELETE FROM tblPontoFinal
	WHERE idPontoFinal = @idPontoFinal

END



DROP PROCEDURE IF EXISTS spPontoFinal_BuscaPorId
GO
CREATE PROCEDURE spPontoFinal_BuscaPorId(
	@idPontoFinal int
)
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR UM PONTO FINAL/TERMINAL ESPECIFICO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spPontoFinal_BuscaPorId @idPontoFinal = 4563
*/
---------------------------------------------------------
AS
BEGIN

	SELECT * FROM tblPontoFinal
	WHERE idPontoFinal = @idPontoFinal

END



DROP PROCEDURE IF EXISTS spPontoFinal_BuscaTodos
GO
CREATE PROCEDURE spPontoFinal_BuscaTodos
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR TODOS PONTOS FINAIS/TERMINAIS ESPECIFICOS
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spPontoFinal_BuscaTodos
*/
---------------------------------------------------------
AS
BEGIN

	SELECT * FROM tblPontoFinal

END



DROP PROCEDURE IF EXISTS spPontoFinal_BuscaUltimoId
GO
CREATE PROCEDURE spPontoFinal_BuscaUltimoId
--------------------------------------------------------
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRI��O: PROC PARA BUSCAR O ULTIMO PONTO FINAL/TERMINAL INSERIDO
||	DATA: 17/03/2020
||	ATUALIZA��ES: CRIA��O (17/03/2020)
||	
||	EXEMPLO: EXEC spPontoFinal_BuscaUltimoId
*/
---------------------------------------------------------
AS
BEGIN

	SELECT MAX(idPontoFinal) AS idPontoFinal FROM tblPontoFinal

END
