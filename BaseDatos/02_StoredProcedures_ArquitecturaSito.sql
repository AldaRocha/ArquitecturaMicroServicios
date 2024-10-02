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

DROP PROCEDURE IF EXISTS guardarPerfil;
DELIMITER $$
CREATE PROCEDURE guardarPerfil(	 IN		var_Nombre		VARCHAR(45)
								,IN		var_Activo		TINYINT
                                
                                ,OUT	var_PerfilId	INT
)
BEGIN
	INSERT INTO perfil(Nombre, Activo, TiempoSesion, TiempoInactividad, SesionesSimultaneas) VALUES(var_Nombre, var_Activo, 420, 15, 1);
    
    SET var_PerfilId = LAST_INSERT_ID();
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS actualizarPerfil;
DELIMITER $$
CREATE PROCEDURE actualizarPerfil(	 IN		var_PerfilId	INT
									,IN		var_Nombre		VARCHAR(45)
									,IN		var_Activo		TINYINT
)
BEGIN
	UPDATE	perfil
    SET		Nombre = var_Nombre, Activo = var_Activo
    WHERE	PerfilId = var_PerfilId;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS guardarUsuario;
DELIMITER $$
CREATE PROCEDURE guardarUsuario(	 IN		var_PersonaId				INT
									,IN		var_TipoUsuarioId			INT
									,IN		var_Cuenta					VARCHAR(45)
									,IN		var_Contrasena				VARCHAR(150)
									,IN		var_Email					VARCHAR(100)
									,IN		var_Activo					TINYINT
                                
									,IN		var_PerfilId				INT
                                    
                                    ,OUT	var_UsuarioId				INT
                                    ,OUT	var_UsuarioPorPerfilId		INT
)
BEGIN
	INSERT INTO usuario(PersonaId, TipoUsuarioId, Cuenta, Contrasena, Email, ReiniciarContrasena, Activo)
				 VALUES(var_PersonaId, var_TipoUsuarioId, var_Cuenta, var_Contrasena, var_Email, 1, var_Activo);
                 
	SET		var_UsuarioId = LAST_INSERT_ID();
    
    INSERT INTO usuarioporperfil(UsuarioId, PerfilId) VALUES(var_UsuarioId, var_PerfilId);
    
    SET 	var_UsuarioPorPerfilId = LAST_INSERT_ID();
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS actualizarUsuario;
DELIMITER $$
CREATE PROCEDURE actualizarUsuario(	 IN		var_UsuarioId				INT
									,IN		var_PersonaId				INT
									,IN		var_TipoUsuarioId			INT
									,IN		var_Cuenta					VARCHAR(45)
									,IN		var_Email					VARCHAR(100)
									,IN		var_Activo					TINYINT
                                
									,IN		var_PerfilId				INT
)
BEGIN
	UPDATE	usuario
	SET		PersonaId = var_PersonaId, TipoUsuarioId = var_TipoUsuarioId, Cuenta = var_Cuenta, Email = var_Email, Activo = var_Activo
	WHERE	UsuarioId = var_UsuarioId;
    
    UPDATE	usuarioporperfil
    SET 	PerfilId = var_PerfilId
    WHERE	UsuarioId = var_UsuarioId;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS cambiarContraseña;
DELIMITER $$
CREATE PROCEDURE cambiarContraseña(	 IN	var_UsuarioId		INT
									,IN	var_Contrasena		VARCHAR(150)
)
BEGIN
	UPDATE	usuario
    SET		Contrasena = var_Contrasena
    WHERE	UsuarioId = var_UsuarioId;
END $$
DELIMITER ;
