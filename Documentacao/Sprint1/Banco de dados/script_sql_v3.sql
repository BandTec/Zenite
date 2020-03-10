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
	codigoDispostivo varchar(20) not null,
	fkTipo int foreign key references tblTipoDispositivo(idTipoDispositivo)
)

DROP TABLE IF EXISTS tblCargo
GO
CREATE TABLE tblCargo(
	idCargo int primary key IDENTITY,
	descricao varchar(20)
)

DROP TABLE IF EXISTS tblTelefone
GO
CREATE TABLE tblTelefone (
	idTelefone int PRIMARY KEY IDENTITY,
	numero varchar(12) not null
)

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
	fkRFID int foreign key references tblDispositivo(idDispositivo),
	fkConta int foreign key references tblConta(idConta),
	fkCargo int foreign key references tblCargo(idCargo),
	fkTelefone int foreign key references tblTelefone(idTelefone),
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
	fkConta int foreign key references tblConta(idConta),
	fkCargo int foreign key references tblCargo(idCargo),
	fkTelefone int foreign key references tblTelefone(idTelefone),
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
	fkConta int foreign key references tblConta(idConta),
	fkCargo int foreign key references tblCargo(idCargo),
	fkTelefone int foreign key references tblTelefone(idTelefone),
	fkEndereco int foreign key references tblEndereco(idEndereco)
)

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
	fkLinha int foreign key references tblLinha(idLinha),
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

---Pensar na tabela de organização por arduino/rfid/dados viagem....

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
||	DESCRIÇÃO: PROC PARA CADASTRO DE ADMINS
||	DATA: 07/03/2020
||	ATUALIZAÇÕES: CRIAÇÃO (07/03/2020)
||	
||	EXEMPLO: EXEC spCadastroAdmin_i @email = 'alexbuarque20@gmail.com', @senha = '123456789', @nome = 'Alex Buarque da Silva Gusmão'
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
||	DESCRIÇÃO: PROC PARA LOGIN DE USUARIO
||	DATA: 07/03/2020
||	ATUALIZAÇÕES: CRIAÇÃO (07/03/2020)
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
||	DESCRIÇÃO: PROC PARA BUSCA DOS NIVEIS DE USUÁRIOS DO SISTEMA
||	DATA: 07/03/2020
||	ATUALIZAÇÕES: CRIAÇÃO (07/03/2020)
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