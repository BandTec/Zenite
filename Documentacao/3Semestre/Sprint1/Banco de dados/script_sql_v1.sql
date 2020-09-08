CREATE TABLE tblNivel (
	idNivel int primary key IDENTITY,
	descricao varchar(100) not null
)

CREATE TABLE tblConta (
	idConta int primary key identity,
	email varchar(60) not null,
	senha varchar(255) not null,
	fkNivel int foreign key references tblNivel(idNivel)
)

CREATE TABLE tblCargo(
	idCargo int primary key IDENTITY,
	descricao varchar(20)
)

CREATE TABLE tblTelefone (
	idTelefone int PRIMARY KEY IDENTITY,
	ddd varchar(3) not null,
	numero varchar(9) not null
)

CREATE TABLE tblEndereco (
	idEndereco int primary key IDENTITY,
	CEP varchar(8) not null,
	logradouro varchar(120) not null,
	numero varchar(16) not null,
	complemento varchar(60),
	cidade varchar(40),
	estado varchar(2)
)

CREATE TABLE tblFuncionario (
	idFuncionario int primary key IDENTITY,
	nomeFuncionario varchar(100) not null,
	cpf varchar(14) not null unique,
	dtNasc date not null,
	fkConta int foreign key references tblConta(idConta),
	fkCargo int foreign key references tblCargo(idCargo),
	fkTelefone int foreign key references tblTelefone(idTelefone),
	fkEndereco int foreign key references tblEndereco(idEndereco)
)

CREATE TABLE tblPontoFinal (
	idPontoFinal int primary key IDENTITY,
	nomeTerminal varchar(80) not null
)

CREATE TABLE tblLinha (
	idLinha int primary key IDENTITY,
	numeroLinha varchar(7) not null,
	fkPontoIda int foreign key REFERENCES tblPontoFinal(idLinha),
	fkPontoVolta int foreign key REFERENCES tblPontoFinal(idLinha)
)

CREATE TABLE tblCarro(
	idCarro int primary key IDENTITY,
	numeroCarro varchar(10) not null,
	fkLinha int foreign key references tblLinha(idLinha)	
)


