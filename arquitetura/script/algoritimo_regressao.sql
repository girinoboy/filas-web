-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ST_REG_LIN`(V_TAB VARCHAR(100),
                            V_COLX VARCHAR(100),
			    V_COLY VARCHAR(100),
                            V_PREV_VAL DOUBLE(20,4))
BEGIN

-- CRIAÇÃO DAS VARIÁVEIS UTILIZADAS NA PROCEDURE
	DECLARE	V_MED_Y DOUBLE(20,4) ;-- MÉDIA DOS VALORES DE X

	DECLARE V_CMD VARCHAR(8000); -- COMANDOS CONSTRUÍDOS DINAMICAMENTE

	DECLARE V_S , V_I , V_RET DOUBLE(20,4);

	DECLARE V_EQUACAO VARCHAR(100); -- A EQUACAO A SER MOSTRADA
	DECLARE V_R2 DOUBLE(20,4);    -- O VALOR R QUADRADO

	SELECT 'TABELAS TEMPORÁRIAS';
-- TABELAS TEMPORÁRIAS UTILIZADAS NA PROCEDURE	
	DROP TABLE if exists tb_tmp;
	CREATE TEMPORARY TABLE if not exists TB_TMP
	(
		N INT,   		-- QTD DE LINHAS
		SX DOUBLE(20,4),       -- SOMATÓRIO DE X
	        SY DOUBLE(20,4),       -- SOMATÓRIO DE Y
		S_X_Y DOUBLE(20,4),    -- SOMATÓRIO DE X * Y
		SX_Q DOUBLE(20,4),     -- SOMATÓRIO DE X QUADRADO
	        MED_X DOUBLE(20,4),    -- MÉDIA DE X
	        MED_Y DOUBLE(20,4)     -- MÉDIA DE Y
	
	)ENGINE=MEMORY;
	DROP TABLE if exists TB_R2;
	CREATE TEMPORARY TABLE if not exists TB_R2
	(
		R2 DOUBLE(20,4)
	)ENGINE=MEMORY;

	SELECT 'CHECAGEM DA EXISTÊNCIA DA TABELA E DAS COLUNAS';
-- CHECAGEM DA EXISTÊNCIA DA TABELA E DAS COLUNAS

	IF NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = V_TAB) then
	BEGIN
		SELECT 'TABELA NAO EXISTE NA BASE' AS ERRO;
		 -- RETURN
	END;
	END IF;

	IF NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = V_TAB AND COLUMN_NAME = V_COLX) then
	BEGIN
		SELECT 'COLUNA ' + V_COLX + ' NÃO EXISTE NA TABELA ' + V_TAB AS ERRO;
		-- RETURN
	END;
	END IF;

	IF NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = V_TAB AND COLUMN_NAME = V_COLY) then
	BEGIN
		SELECT 'COLUNA ' + V_COLY + ' NÃO EXISTE NA TABELA ' + V_TAB AS ERRO;
		-- RETURN
	END;
	END IF;

	SELECT 'CALCULANDO OS VALORES DAS COLUNAS DA TABELA TEMPORÁRIA #TB_TMP';
-- CALCULANDO OS VALORES DAS COLUNAS DA TABELA TEMPORÁRIA #TB_TMP
	SET V_CMD = ' SELECT COUNT(*)';SELECT V_CMD;
	SET V_CMD = concat(V_CMD , ', SUM(' , V_COLX , ')');SELECT V_CMD;
	SET V_CMD = concat(V_CMD , ', SUM(' , V_COLY , ')');SELECT V_CMD;
	SET V_CMD = concat(V_CMD , ', SUM(' , V_COLX , '*' , V_COLY , ')');SELECT V_CMD;
	SET V_CMD = concat(V_CMD , ', SUM(' , V_COLX , '*' , V_COLX , ')');SELECT V_CMD;
	SET V_CMD = concat(V_CMD , ', AVG(' , V_COLX , ')');SELECT V_CMD;
	SET V_CMD = concat(V_CMD , ', AVG(' , V_COLY , ')');SELECT V_CMD;
	SET V_CMD = concat(V_CMD ,' FROM ' , V_TAB,';');SELECT V_CMD;

	SET @s = CONCAT('INSERT TB_TMP ',V_CMD );
	PREPARE stmt3 FROM @s;
	EXECUTE stmt3;
	drop prepare stmt3;

	SELECT 'TERMINADO INSERCAO';

	SELECT MED_Y  into  V_MED_Y
	FROM TB_TMP;

	SELECT 'CALCULANDO O COEFICIENTE DE X NA EQUAÇÃO';
	-- CALCULANDO O COEFICIENTE DE X NA EQUAÇÃO
	SELECT  ((N*S_X_Y - (SX*SY)) / (N*SX_Q-(SX*SX))) into V_S
	FROM TB_TMP; 

select CONCAT('valor V_S: ',V_S );

	SELECT 'CALCULANDO O PONTO ONDE A RETA TOCA O EIXO DO X';
	-- CALCULANDO O PONTO ONDE A RETA TOCA O EIXO DO X
	SELECT  MED_Y - V_S*MED_X into V_I 
	FROM TB_TMP   ; 

 

select v_i;
      

	SELECT 'MONTANDO A EQUACAO';-- MONTANDO A EQUACAO
	SET V_EQUACAO = concat('Y = ' , CONVERT(V_S,CHAR(50)) , '*X ');

	SELECT 'CALCULANDO O VALOR DESEJADO';-- CALCULANDO O VALOR DESEJADO
	SET V_RET = V_S*V_PREV_VAL + V_I;

	IF V_I >= 0 then
	BEGIN

		SET V_EQUACAO = concat(V_EQUACAO , '+ ' , CONVERT(V_I,CHAR(50)));
	END;
	ELSE
	BEGIN

		SET V_EQUACAO = concat(V_EQUACAO , ' ' , CONVERT(V_I,CHAR(50)));

	END;
	END IF;

	SELECT 'CALCULANDO O R QUADRADO';
	-- CALCULANDO O R QUADRADO

	SET V_CMD = 'SELECT 1 - (SUM(';
	-- SET V_CMD = concat(V_CMD , '(' , V_COLY , '- (', CONVERT(V_S,CHAR(50)));
	SET V_CMD = concat(V_CMD , '(' , V_COLY , '- (' , CONVERT(V_S,CHAR(50)) , '*' , V_COLX ,  '+' , CONVERT(V_I,CHAR(50)) , '))*');
	SET V_CMD = concat(V_CMD , '(' , V_COLY , '- (' , CONVERT(V_S,CHAR(50)) , '*' , V_COLX ,  '+' ,  CONVERT(V_I,CHAR(50)) , ')) )/');
	SET V_CMD = concat(V_CMD , 'SUM(   (' , V_COLY , '- ' ,  CONVERT(V_MED_Y,CHAR(50)) , ') * (' , V_COLY , '- '  , CONVERT(V_MED_Y,CHAR(50)) , ') ) )');
	SET V_CMD = concat(V_CMD , ' FROM ' , V_TAB);
	select concat('r selecionado1:',V_CMD);
	select V_CMD;
	select concat('r selecionado2:',V_CMD);
select v_i;

	SET @i = CONCAT('INSERT TB_R2 ',V_CMD );
	PREPARE stmt2 FROM @i;
	EXECUTE stmt2;
	drop prepare stmt2;
/*
	INSERT TB_R2
	select (V_CMD);
*/
	DROP TABLE if exists TB_RESPOSTA;
	CREATE TEMPORARY TABLE if not exists TB_RESPOSTA
	(
		EQUACAO varchar(100),
		VAL_PREVISTO DOUBLE(20,4),
		R2 DOUBLE(20,4), 
		modelo varchar(100)
	)ENGINE=MEMORY;
	SELECT 'MOSTRANDO OS DADOS';
	-- MOSTRANDO OS DADOS
	IF (SELECT R2 FROM TB_R2) >= 0.8 then
	BEGIN
	INSERT TB_RESPOSTA	SELECT V_EQUACAO AS EQUACAO
                      ,V_RET AS VAL_PREVISTO
		      , (SELECT R2 FROM TB_R2) AS R2
		      , 'MODELO LINEAR' AS MODELO;
	SELECT V_EQUACAO AS EQUACAO
                      ,V_RET AS VAL_PREVISTO
		      , (SELECT R2 FROM TB_R2) AS R2
		      , 'MODELO LINEAR' AS MODELO;

	END;
	ELSE
	BEGIN

	INSERT TB_RESPOSTA	SELECT V_EQUACAO AS EQUACAO
                      ,V_RET AS VAL_PREVISTO
		      , (SELECT R2 FROM TB_R2) AS R2
		      , 'MODELO NÃO LINEAR' AS MODELO;
	SELECT V_EQUACAO AS EQUACAO
                      ,V_RET AS VAL_PREVISTO
		      , (SELECT R2 FROM TB_R2) AS R2
		      , 'MODELO NÃO LINEAR' AS MODELO;
	
	END;
	END IF;
END