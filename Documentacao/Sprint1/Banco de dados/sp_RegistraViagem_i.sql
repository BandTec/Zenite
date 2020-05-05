DROP PROCEDURE IF EXISTS sp_RegistraViagem_i
GO

CREATE PROCEDURE sp_RegistraViagem_i (
	@arduinoSerial varchar(60),
	@codigoDispositivo varchar(40)
)
/*
||	PROC CRIADA POR: ALEX GUSMAO
||	DESCRIÇÃO: PROC PARA CADASTRO DAS VIAGENS
||	DATA: 04/05/2020
||	ATUALIZAÇÕES: CRIAÇÃO (04/05/2020)
||	
||	EXEMPLO: EXEC sp_RegistraViagem_i @arduinoSerial = '757353230303511120E1', @codigoDispositivo = '69 BE 4A B8'
*/
AS
BEGIN
	DECLARE @ID_CARRO INT
	DECLARE @ID_FISCAL INT
	DECLARE @ID_LINHA INT
	DECLARE @ID_MOTORISTA INT

	SELECT @ID_CARRO = C.id_carro FROM tbl_dispositivo D 
	INNER JOIN tbl_carro C ON C.fk_dispositivo = D.id_dispositivo
	where d.codigo_dispositivo = @arduinoSerial

	SELECT @ID_FISCAL = F.id_fiscal FROM tbl_dispositivo D 
	INNER JOIN tbl_fiscal F ON F.fk_dispositivo = d.id_dispositivo
	where d.codigo_dispositivo = @codigoDispositivo

	SELECT @ID_LINHA = CL.id_linha from tbl_carro C 
	INNER JOIN tbl_carro_linha CL ON C.id_carro = CL.id_carro
	where C.id_carro = @ID_CARRO

	SELECT @ID_MOTORISTA = MC.id_motorista from tbl_carro C 
	INNER JOIN tbl_motorista_carro MC ON C.id_carro = MC.id_carro
	where C.id_carro = @ID_CARRO

	DECLARE @ID_VIAGEM INT

	SELECT @ID_VIAGEM = DV.id_dados_viagem FROM tbl_dados_viagem DV
	where dv.id_carro = @ID_CARRO
	and DV.id_fiscal = @ID_FISCAL
	and DV.id_linha = @ID_LINHA
	AND DV.id_motorista = @ID_MOTORISTA

	IF (@ID_VIAGEM IS NULL)
	BEGIN
		BEGIN TRY
			INSERT INTO tbl_dados_viagem values (
				null, SWITCHOFFSET (SYSDATETIMEOFFSET(), '-03:00'), NULL, @ID_CARRO, @ID_FISCAL, @ID_LINHA, @ID_MOTORISTA
			)
			SELECT 'Viagem Iniciada' as Resposta
		END TRY
		BEGIN CATCH
			SELECT 'ERRO AO INICIAR A VIAGEM, VERIFIQUE OS DADOS DO DISPOSITIVO E/OU DO CARTÃO' as Resposta
		END CATCH
		
	END
	ELSE
	BEGIN
		DECLARE @HORA_CHEGADA DATETIME2(7)
		SELECT @HORA_CHEGADA = DV.hora_chegada from tbl_dados_viagem DV
		WHERE DV.id_dados_viagem = @ID_VIAGEM

		IF (@HORA_CHEGADA IS NULL)
		BEGIN
			UPDATE tbl_dados_viagem set hora_chegada = SWITCHOFFSET (SYSDATETIMEOFFSET(), '-03:00') 
			WHERE id_dados_viagem = @ID_VIAGEM

			SELECT 'Viagem Finalizada' as Resposta
		END
		ELSE
		BEGIN

			BEGIN TRY
				INSERT INTO tbl_dados_viagem values (
					NULL, SWITCHOFFSET (SYSDATETIMEOFFSET(), '-03:00'), NULL, @ID_CARRO, @ID_FISCAL, @ID_LINHA, @ID_MOTORISTA
				)
			SELECT 'Viagem Iniciada' as Resposta
			END TRY
			BEGIN CATCH
				SELECT 'ERRO AO INICIAR A VIAGEM, VERIFIQUE OS DADOS DO DISPOSITIVO E/OU DO CARTÃO' as Resposta
			END CATCH
			
		END
	END
	
END