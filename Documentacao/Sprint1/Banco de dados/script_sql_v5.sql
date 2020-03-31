--------------------------------------------------------------------INICIO TABELAS--------------------------------------------------------------------
DROP TABLE IF EXISTS tblNivel
GO
CREATE TABLE tblNivel (
	idNivel int primary key IDENTITY,
	descricao varchar(100) not null
)

DROP TABLE IF EXISTS tblConta
GO
CREATE TABLE tblConta (
	idConta int primary key identity,
	email varchar(60) not null,
	senha varchar(255) not null,
	fkNivel int foreign key references tblNivel(idNivel)
)

DROP TABLE IF EXISTS tblTipoDispositivo
GO
CREATE TABLE tblTipoDispositivo(
	idTipoDispositivo int primary key IDENTITY,
	descricao varchar(10) not null
)

DROP TABLE IF EXISTS tblDispositivo
GO
CREATE TABLE tblDispositivo (
	idDispositivo int primary key IDENTITY,
	codigoDispositivo varchar(20) not null,
	fkTipo int foreign key references tblTipoDispositivo(idTipoDispositivo)
)

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

DROP TABLE IF EXISTS tblEndereco
GO
CREATE TABLE tblEndereco (
	idEndereco int primary key IDENTITY,
	CEP varchar(8) not null,
	logradouro varchar(120) not null,
	numero varchar(16) not null,
	complemento varchar(60),
	cidade varchar(40),
	estado varchar(2)
)

DROP TABLE IF EXISTS tblAdmin
GO
CREATE TABLE tblAdmin(
	idAdmin int primary key IDENTITY,
	nome varchar(100) not null,
	fkConta int foreign key references tblConta(idConta)
)

DROP TABLE IF EXISTS tblMotorista
GO
CREATE TABLE tblMotorista(
	idMotorista int primary key IDENTITY,
	nomeMotorista varchar(100) not null,
	cpf varchar(14) not null unique,
	cnh varchar(11) not null unique,
	dtNasc date not null,
	telefone varchar(11) not null,
	--fkRFID int foreign key references tblDispositivo(idDispositivo),
	fkConta int foreign key references tblConta(idConta),
	fkEndereco int foreign key references tblEndereco(idEndereco)
)

DROP TABLE IF EXISTS tblFiscal
GO
CREATE TABLE tblFiscal(
	idFiscal int primary key IDENTITY,
	nomeFiscal varchar(100) not null,
	cpf varchar(14) not null unique,
	registroFiscal varchar(20) not null unique,
	dtNasc date not null,
	telefone varchar(11) not null,
	fkConta int foreign key references tblConta(idConta),
	fkEndereco int foreign key references tblEndereco(idEndereco),
	fkDispositivo int foreign key references tblDispositivo(idDispositivo)
)

DROP TABLE IF EXISTS tblGerente
GO
CREATE TABLE tblGerente(
	idGerente int primary key IDENTITY,
	nomeGerente varchar(100) not null,
	cpf varchar(14) not null unique,
	dtNasc date not null,
	telefone varchar(11) not null,
	fkConta int foreign key references tblConta(idConta),
	fkEndereco int foreign key references tblEndereco(idEndereco)
)

--N�o estamos mais de um telefone mais caso isso seja preciso essa ser� a estrutura da tabela de associa��o
--DROP TABLE IF EXISTS tblTelefone_Funcionario
--GO
--CREATE TABLE tblTelefone_Funcionario(
--	idTelefone int foreign key references tblTelefone(idTelefone),
--	idFuncionario int not null,
--	cargoFunc int not null
--)

DROP TABLE IF EXISTS tblPontoFinal
GO
CREATE TABLE tblPontoFinal (
	idPontoFinal int primary key IDENTITY,
	nomeTerminal varchar(80) not null
)

DROP TABLE IF EXISTS tblLinha
GO
CREATE TABLE tblLinha (
	idLinha int primary key IDENTITY,
	numeroLinha varchar(7) not null,
	fkPontoIda int foreign key REFERENCES tblPontoFinal(idPontoFinal),
	fkPontoVolta int foreign key REFERENCES tblPontoFinal(idPontoFinal)
)

DROP TABLE IF EXISTS tblCarro
GO
CREATE TABLE tblCarro(
	idCarro int primary key IDENTITY,
	numeroCarro varchar(10) not null unique,
	fkDispositivo int foreign key references tblDispositivo(idDispositivo)	
)

DROP TABLE IF EXISTS tblMotorista_Carro
GO
CREATE TABLE tblMotorista_Carro(
	idMotorista int foreign key references tblMotorista(idMotorista),
	idCarro int foreign key references tblCarro(idCarro),
	primary key(idMotorista, idCarro)
)

DROP TABLE IF EXISTS tblCarro_Linha
GO
CREATE TABLE tblCarro_Linha(
	idCarro int foreign key references tblCarro(idCarro),
	idLinha int foreign key references tblLinha(idLinha),
	primary key(idCarro, idLinha)
)

DROP TABLE IF EXISTS tblDadosViagem
GO
CREATE TABLE tblDadosViagem(
	idDadosViagem int primary key IDENTITY(1000,1),
	idCarro int foreign key references tblCarro(idCarro),
	idLinha int foreign key references tblLinha(idLinha),
	idMotorista int foreign key references tblMotorista(idMotorista),
	idFiscal int foreign key references tblFiscal(idFiscal),
	horaSaida datetime not null,
	horaChegada datetime,
	qtdPassageiros int
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
