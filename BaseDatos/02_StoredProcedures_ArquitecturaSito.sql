-- ---------------------------------------------------------------------------- --
-- Archivo: 02_StoredProcedures_ArquitecturaSito.sql                      		--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 21-09-2024                                 			--
-- ---------------------------------------------------------------------------- --

USE arquitectura_sito;

DROP PROCEDURE IF EXISTS generarToken;
DELIMITER $$
CREATE PROCEDURE generarToken(	IN	var_UsuarioId	INT,			-- 1
								IN	var_LastToken	VARCHAR(65)		-- 2
)
BEGIN
	-- Actualizamos la tabla usuario --
	UPDATE	usuario
    
    -- Aplicamos los cambios --
    SET		LastToken = var_LastToken,
			DateLastToken = CURRENT_TIMESTAMP
            
	-- Aplicamos un filtro --
    WHERE	UsuarioId=var_UsuarioId;
END $$
DELIMITER ;