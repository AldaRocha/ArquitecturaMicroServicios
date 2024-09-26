-- ---------------------------------------------------------------------------- --
-- Archivo: 03_Vistas_ArquitecturaSito.sql                      				--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 21-09-2024                                 			--
-- ---------------------------------------------------------------------------- --

USE arquitectura_sito;

DROP VIEW IF EXISTS v_empleadosLogin;
CREATE VIEW v_empleadosLogin AS
	SELECT	 u.UsuarioId
			,u.Cuenta
			,u.Contrasena
			,u.Email AS EmailUsuario
			,u.ReiniciarContrasena
			,u.Activo AS ActivoUsuario
			,u.LastToken
			,DATE_FORMAT(u.DateLastToken, '%d/%m/%Y') AS DateLastToken
			
            ,tu.TipoUsuarioId
			,tu.Nombre AS NombreTipoUsuario
			,tu.Activo AS ActivoTipoUsuario
			,tu.Expresion
			,tu.ComentarioExpresion

			,p.PersonaId
			,p.Nombre AS NombrePersona
			,p.Email AS EmailPersona
			,p.Telefono
			,p.Curp
			,p.NumeroEmpleado
			,p.TipoContrato
			,DATE_FORMAT(p.FechaIngreso, '%d/%m/%Y') AS FechaIngreso
			,p.Grupo
			,p.Estatus AS EstatusPersona
            
            ,a.AreaId
			,a.Nombre AS NombreArea
			,a.Estatus AS EstatusArea
            
            ,upp.UsuarioPorPerfilId

			,pe.PerfilId
			,pe.Nombre AS NombrePerfil
			,pe.Activo AS ActivoPerfil
			,pe.TiempoSesion
			,pe.TiempoInactividad
			,pe.SesionesSimultaneas
	FROM	usuario u
			INNER JOIN tipousuario tu ON u.TipoUsuarioId=tu.TipoUsuarioId
            INNER JOIN persona p ON u.PersonaId=p.PersonaId
            INNER JOIN area a ON a.AreaId=p.AreaId
            INNER JOIN usuarioporperfil upp ON u.UsuarioId=upp.UsuarioId
            INNER JOIN perfil pe ON upp.PerfilId=pe.PerfilId;
            
DROP VIEW IF EXISTS v_pantallas;
CREATE VIEW v_pantallas AS
	SELECT	 u.UsuarioId
			
            ,upp.PerfilId
            
            ,p.Nombre AS NombrePerfil
            
            ,ppp.PermisoPorPantallaId
            
            ,pppa.PermisoId
            ,pppa.PantallaId
            
            ,pso.Identificador AS IdentificadorPermiso
            ,pso.Nombre AS NombrePermiso
            
            ,plla.ModuloId
            ,plla.Identificador AS IdentificadorPantalla
            ,plla.Nombre AS NombrePantalla
            ,plla.Ruta
            
            ,m.Identificador AS IdentificadorModulo
            ,m.Nombre AS NombreModulo
    FROM	usuario u
			INNER JOIN usuarioporperfil upp ON u.UsuarioId = upp.UsuarioId
            INNER JOIN perfil p ON upp.PerfilId = p.PerfilId
            INNER JOIN permisoporperfil ppp ON p.PerfilId = ppp.PerfilId
            INNER JOIN permisoporpantalla pppa ON ppp.PermisoPorPantallaId = pppa.PermisoPorPantallaId
            INNER JOIN permiso pso ON pppa.PermisoId = pso.PermisoId
            INNER JOIN pantalla plla ON pppa.PantallaId = plla.PantallaId
            INNER JOIN modulo m ON plla.ModuloId = m.ModuloId
	ORDER BY PermisoPorPantallaId ASC;



	SELECT	 u.UsuarioId
			
            ,upp.PerfilId
            
            ,p.Nombre AS NombrePerfil
            
            ,ppp.PermisoPorPantallaId
            
            ,pppa.PermisoId
            ,pppa.PantallaId
            
            ,pso.Identificador AS IdentificadorPermiso
            ,pso.Nombre AS NombrePermiso
            
            ,plla.ModuloId
            ,plla.Identificador AS IdentificadorPantalla
            ,plla.Nombre AS NombrePantalla
            ,plla.Ruta
            
            ,m.Identificador AS IdentificadorModulo
            ,m.Nombre AS NombreModulo
    FROM	usuario u
			INNER JOIN usuarioporperfil upp ON u.UsuarioId = upp.UsuarioId
            INNER JOIN perfil p ON upp.PerfilId = p.PerfilId
            INNER JOIN permisoporperfil ppp ON p.PerfilId = ppp.PerfilId
            INNER JOIN permisoporpantalla pppa ON ppp.PermisoPorPantallaId = pppa.PermisoPorPantallaId
            INNER JOIN permiso pso ON pppa.PermisoId = pso.PermisoId
            INNER JOIN pantalla plla ON pppa.PantallaId = plla.PantallaId
            INNER JOIN modulo m ON plla.ModuloId = m.ModuloId
	ORDER BY PermisoPorPantallaId ASC;