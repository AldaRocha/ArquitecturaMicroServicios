-- ---------------------------------------------------------------------------- --
-- Archivo: 04_DatosBase_ArquitecturaSito.sql                      				--
-- Version: 1.0                                                     			--
-- Autor:   Francisco Javier Rocha Aldana   									--
-- Email:   rochaaldanafcojavier@gmail.com / 21000459@alumnos.utleon.edu.mx		--
-- Fecha de elaboracion: 21-09-2024                                 			--
-- ---------------------------------------------------------------------------- --

USE arquitectura_sito;

-- Insertamos un registro al area con el fin de que nos permita registrar una persona con una area correspondiente 
INSERT INTO area(Nombre, Estatus) VALUES("Informática", 1);
INSERT INTO area(Nombre, Estatus) VALUES("Servicios Escolares", 1);
INSERT INTO area(Nombre, Estatus) VALUES("Recursos Humanos", 1);
INSERT INTO area(Nombre, Estatus) VALUES("Docente", 1);
INSERT INTO area(Nombre, Estatus) VALUES("Alumno", 1);

-- Insertamos la informacion de la persona
INSERT INTO persona(Nombre, Email, Telefono, Curp, NumeroEmpleado, TipoContrato, FechaIngreso, Grupo, Estatus, AreaId)
			 VALUES("Francisco Javier", "21000459@alumnos.utleon.edu.mx", "4778594709", "ARQJ001002USTCLWA0", 21000459, "Base", STR_TO_DATE("23/09/2024", '%d/%m/%Y'), "", 1, (SELECT MAX(AreaId) FROM area));
             
INSERT INTO tipousuario(Nombre, Activo, Expresion, ComentarioExpresion) VALUES("Informática", 1, "((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,30})", "La contraseña debe tener mínimo 6 caracteres y máximo 30. Debe contener al menos un letra mayúscula, una minúscula y un número.");
INSERT INTO tipousuario(Nombre, Activo, Expresion, ComentarioExpresion) VALUES("General", 1, "((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,10})", "La contraseña debe tener mínimo 6 caracteres y máximo 10. Debe contener al menos un letra mayúscula, una minúscula y un número.");

INSERT INTO usuario(PersonaId, TipoUsuarioId, Cuenta, Contrasena, Email, ReiniciarContrasena, Activo) VALUES(1, 1, "Alda.ts", "aeec78a2ec7aa5c159d7d8e9d4242690611948368c1f55b68528411565f691bf", "21000459@alumnos.utleon.edu.mx", 1, 1);

INSERT INTO perfil(Nombre, Activo, TiempoSesion, TiempoInactividad, SesionesSimultaneas) VALUES("Informática", 1, 420, 15, 1);
INSERT INTO perfil(Nombre, Activo, TiempoSesion, TiempoInactividad, SesionesSimultaneas) VALUES("Servicios Escolares", 1, 420, 15, 1);
INSERT INTO perfil(Nombre, Activo, TiempoSesion, TiempoInactividad, SesionesSimultaneas) VALUES("Recursos Humanos", 1, 420, 15, 1);
INSERT INTO perfil(Nombre, Activo, TiempoSesion, TiempoInactividad, SesionesSimultaneas) VALUES("Docente", 1, 420, 15, 1);
INSERT INTO perfil(Nombre, Activo, TiempoSesion, TiempoInactividad, SesionesSimultaneas) VALUES("Alumno", 1, 420, 15, 1);

INSERT INTO usuarioporperfil(UsuarioId, PerfilId) VALUES((SELECT MAX(UsuarioId) FROM usuario), (SELECT MAX(PerfilId) FROM perfil));

INSERT INTO permiso(Identificador, Nombre, Activo, Tipo) VALUES("consultar", "Consultar", 1, 1);
INSERT INTO permiso(Identificador, Nombre, Activo, Tipo) VALUES("editar", "Editar", 1, 1);
INSERT INTO permiso(Identificador, Nombre, Activo, Tipo) VALUES("agregar", "Agregar", 1, 1);
INSERT INTO permiso(Identificador, Nombre, Activo, Tipo) VALUES("eliminar", "Eliminar", 1, 1);

INSERT INTO modulo(Identificador, Nombre, Activo) VALUES("M1", "Administración de usuarios del sistema", 1);
INSERT INTO modulo(Identificador, Nombre, Activo) VALUES("M2", "Proceso de selección", 1);
INSERT INTO modulo(Identificador, Nombre, Activo) VALUES("M3", "Administración de personal", 1);
INSERT INTO modulo(Identificador, Nombre, Activo) VALUES("M4", "Administración de grupos", 1);
INSERT INTO modulo(Identificador, Nombre, Activo) VALUES("M5", "Becas", 1);

INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(1, "M11", "Catálogo de roles", 1, "../administracionusuariossistema/catalogoderoles/vista_catalogoderoles.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(1, "M12", "Catálogo de usuarios", 1, "../administracionusuariossistema/catalogodeusuarios/vista_catalogodeusuarios.html", "");

INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(2, "M21", "Catálogo de periodos de inscripción", 1, "../procesodeseleccion/catalogodeperiodosdeinscripcion/vista_catalogodeperiodosdeinscripcion.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(2, "M22", "Validación de resultados", 1, "../procesodeseleccion/validacionderesultados/vista_validacionderesultados.html", "");

INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(3, "M31", "Areas", 1, "../administraciondepersonal/areas/vista_areas.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(3, "M32", "Catálogo de personal", 1, "../administraciondepersonal/catalogodepersonal/vista_catalogodepersonal.html", "");

INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(4, "M41", "Catálogo de carreras", 1, "../administraciondegrupos/catalogodecarreras/vista_catalogodecarreras.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(4, "M42", "Grupos", 1, "../administraciondegrupos/grupos/vista_grupos.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(4, "M43", "Profesores", 1, "../administraciondegrupos/profesores/vista_profesores.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(4, "M44", "Alumnos", 1, "../administraciondegrupos/alumnos/vista_alumnos.html", "");

INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(5, "M51", "Catálogo de becas", 1, "../becas/catalogodebecas/vista_catalogodebecas.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(5, "M52", "Registro de solicitudes", 1, "../becas/registrodesolicitudes/vista_registrodesolicitudes.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(5, "M53", "Validación de solicitudes", 1, "../becas/validaciondesolicitudes/vista_validaciondesolicitudes.html", "");
INSERT INTO pantalla(ModuloId, Identificador, Nombre, Activo, Ruta, Icono) VALUES(5, "M54", "Lista de resultados", 1, "../becas/listaderesultados/vista_listaderesultados.html", "");

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 1);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 1);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 1);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 1);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 2);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 2);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 2);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 2);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 3);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 3);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 3);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 3);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 4);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 4);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 4);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 4);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 5);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 5);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 5);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 5);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 6);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 6);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 6);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 6);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 7);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 7);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 7);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 7);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 8);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 8);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 8);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 8);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 9);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 9);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 9);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 9);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 10);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 10);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 10);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 10);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 11);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 11);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 11);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 11);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 12);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 12);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 12);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 12);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 13);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 13);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 13);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 13);

INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(1, 14);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(2, 14);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(3, 14);
INSERT INTO permisoporpantalla(PermisoId, PantallaId) VALUES(4, 14);

-- Permisos de pantallas para Perfil "Informática"
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(1, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(2, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(3, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(4, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(5, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(6, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(7, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(8, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(9, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(10, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(11, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(12, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(13, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(14, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(15, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(16, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(17, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(18, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(19, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(20, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(21, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(22, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(23, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(24, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(25, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(26, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(27, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(28, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(29, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(30, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(31, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(32, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(33, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(34, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(35, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(36, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(37, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(38, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(39, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(40, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(41, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(42, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(43, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(44, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(45, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(46, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(47, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(48, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(49, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(50, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(51, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(52, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(53, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(54, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(55, 1);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(56, 1);

-- Permisos de pantallas para Perfil "Servicios Escolares"
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(9, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(10, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(11, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(12, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(13, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(14, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(15, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(16, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(25, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(26, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(27, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(28, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(29, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(30, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(31, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(32, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(41, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(42, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(43, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(44, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(49, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(50, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(51, 2);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(52, 2);

-- Permisos de pantallas para Perfil "Recursos Humanos"
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(17, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(18, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(19, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(20, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(21, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(22, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(23, 3);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(24, 3);

-- Permisos de pantallas para Perfil "Docente"
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(33, 4);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(34, 4);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(35, 4);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(36, 4);

-- Permisos de pantallas para Perfil "Alumno"
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(37, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(38, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(39, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(40, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(45, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(46, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(47, 5);
INSERT INTO permisoporperfil(PermisoPorPantallaId, PerfilId) VALUES(48, 5);
