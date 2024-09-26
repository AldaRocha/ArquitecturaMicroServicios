-- ---------------------------------------------------------------------------- --
-- Archivo: 01_DDL_ArquitecturaSito.sql                                			--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 21-09-2024                                 			--
-- ---------------------------------------------------------------------------- --

-- Eliminamos la base de datos si existe --
DROP DATABASE IF EXISTS arquitectura_sito;

-- Creamos la base de datos --
CREATE DATABASE arquitectura_sito;

-- Seleccionamos la base de datos que usaremos --
USE arquitectura_sito;

CREATE TABLE area(
	 AreaId		INT NOT NULL PRIMARY KEY AUTO_INCREMENT						-- 1
	,Nombre		VARCHAR(100)												-- 2
    ,Estatus	INT															-- 3
);

CREATE TABLE persona(
	 PersonaId			INT NOT NULL PRIMARY KEY AUTO_INCREMENT				-- 1
	,Nombre				VARCHAR(45)											-- 2
	,Email				VARCHAR(100)										-- 3
	,Telefono			VARCHAR(45)											-- 4
	,Curp				VARCHAR(50)											-- 5
	,NumeroEmpleado		INT													-- 6
	,TipoContrato		VARCHAR(50)											-- 7
	,FechaIngreso		DATETIME											-- 8
	,Grupo				VARCHAR(100)										-- 9
	,Estatus			INT													-- 10
	,AreaId				INT													-- 11
        
	,FOREIGN KEY (AreaId) REFERENCES area(AreaId)
);

CREATE TABLE tipousuario(
	 TipoUsuarioId			INT NOT NULL PRIMARY KEY AUTO_INCREMENT			-- 1
	,Nombre					VARCHAR(45)										-- 2
	,Activo					TINYINT											-- 3
	,Expresion				VARCHAR(150)									-- 4
	,ComentarioExpresion	VARCHAR(200)									-- 5
);

CREATE TABLE usuario(
	 UsuarioId				INT NOT NULL PRIMARY KEY AUTO_INCREMENT			-- 1
	,PersonaId				INT												-- 2
	,TipoUsuarioId			INT												-- 3
	,Cuenta					VARCHAR(45)										-- 4
	,Contrasena				VARCHAR(150)									-- 5
	,Email					VARCHAR(100)									-- 6
	,ReiniciarContrasena	TINYINT											-- 7
	,Activo					TINYINT											-- 8
    ,LastToken				VARCHAR(65) NOT NULL DEFAULT ''					-- 9
    ,DateLastToken			DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP		-- 10

	,FOREIGN KEY (PersonaId)		REFERENCES persona(PersonaId)
	,FOREIGN KEY (TipoUsuarioId)	REFERENCES tipousuario(TipoUsuarioId)
);

CREATE TABLE perfil(
	 PerfilId				INT NOT NULL PRIMARY KEY AUTO_INCREMENT			-- 1
	,Nombre					VARCHAR(45)										-- 2
	,Activo					TINYINT											-- 3
	,TiempoSesion			INT												-- 4
	,TiempoInactividad		INT												-- 5
	,SesionesSimultaneas	TINYINT											-- 6
);

CREATE TABLE usuarioporperfil(
	 UsuarioPorPerfilId		INT NOT NULL PRIMARY KEY AUTO_INCREMENT			-- 1
	,UsuarioId				INT NOT NULL									-- 2
	,PerfilId				INT NOT NULL									-- 3

	,FOREIGN KEY (UsuarioId)	REFERENCES usuario(UsuarioId)
	,FOREIGN KEY (PerfilId)		REFERENCES perfil(PerfilId)
);

CREATE TABLE permiso(
	 PermisoId			INT NOT NULL PRIMARY KEY AUTO_INCREMENT				-- 1
	,Identificador		VARCHAR(45)											-- 2
	,Nombre				VARCHAR(45)											-- 3
	,Activo				TINYINT												-- 4
	,Tipo				INT													-- 5
);

CREATE TABLE modulo(
	 ModuloId			INT NOT NULL PRIMARY KEY AUTO_INCREMENT				-- 1
	,Identificador		VARCHAR(45)											-- 2
	,Nombre				VARCHAR(45) NOT NULL								-- 3
	,Activo				TINYINT NOT NULL									-- 4
	,Icono				VARCHAR(255)										-- 5
);

CREATE TABLE pantalla(
	 PantallaId			INT NOT NULL PRIMARY KEY AUTO_INCREMENT				-- 1
	,ModuloId			INT NOT NULL										-- 2
	,Identificador		VARCHAR(45) NOT NULL								-- 3
	,Nombre				VARCHAR(45) NOT NULL								-- 4
	,Activo				TINYINT												-- 5
	,Ruta				VARCHAR(255)										-- 6
	,Icono				VARCHAR(255)										-- 7

	,FOREIGN KEY (ModuloId)		REFERENCES modulo(ModuloId)
);

CREATE TABLE permisoporpantalla(
	 PermisoporpantallaId		INT NOT NULL PRIMARY KEY AUTO_INCREMENT		-- 1
	,PermisoId					INT NOT NULL								-- 2
	,PantallaId					INT NOT NULL								-- 3

	,FOREIGN KEY (PermisoId)	REFERENCES permiso(PermisoId)
	,FOREIGN KEY (PantallaId)	REFERENCES pantalla(PantallaId)
);

CREATE TABLE permisoporperfil(
	 PermisoPorPerfilId			INT NOT NULL PRIMARY KEY AUTO_INCREMENT		-- 1
	,PermisoPorPantallaId		INT NOT NULL								-- 2
	,PerfilId					INT NOT NULL								-- 3

	,FOREIGN KEY (PermisoPorPantallaId)		REFERENCES permisoporpantalla(PermisoporpantallaId)
	,FOREIGN KEY (PerfilId)					REFERENCES perfil(PerfilId)
);