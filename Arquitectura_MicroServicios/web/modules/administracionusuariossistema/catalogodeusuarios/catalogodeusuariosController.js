let usuarios = [];
let perfiles = [];
let tipousuarios = [];
let personas = [];
let UsuarioEditadoId = 0;

export function inicializar(){
    setDetalleVisible(true);
    datosIniciales();
    pedirDatos();
    configureTableFilter(document.getElementById("txtBuscarUsuario"), document.getElementById("tblUsuarios"));
}

export function datosIniciales(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    fetch("../../api/roles/getAll")
            .then(response => {
                return response.json();
            })
                    .then(data => {
                        if(data.error){
                            Swal.fire('', data.error, 'warning');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else if(data.exception){
                            Swal.fire('', "Error interno del servidor.", 'error');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else{
                            llenarSelectRoles(data);
                        }
            });
            
    fetch("../../api/tipousuarios/getAll")
            .then(response => {
                return response.json();
            })
                    .then(data => {
                        if(data.error){
                            Swal.fire('', data.error, 'warning');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else if(data.exception){
                            Swal.fire('', "Error interno del servidor.", 'error');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else{
                            llenarSelectTipoUsuarios(data);
                        }
            });
            
    /*fetch("url de Kevin")
            .then(response => {
                return response.json();
            })
                    .then(data => {
                        if(data.error){
                            Swal.fire('', data.error, 'warning');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else if(data.exception){
                            Swal.fire('', "Error interno del servidor.", 'error');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else{
                            llenarSelectPersona(data);
                        }
            });*/
    
    document.querySelector(".spinner-container").classList.add("d-none");
}

export function llenarSelectRoles(data){
    let cuerpo = '<option selected="">' +
                    'Seleccione el rol:' +                            
                 '</option>';
    perfiles = data;
    perfiles.forEach((p) => {
        let datos = '<option value="' + p.PerfilId + '">' +
                            p.Nombre +
                    '</option>';
        cuerpo+=datos;
    });
    document.getElementById("cmbPerfilId").innerHTML = cuerpo;
}

export function llenarSelectTipoUsuarios(data){
    let cuerpo = '<option selected="">' +
                    'Seleccione el tipo de contraseña:' +                            
                 '</option>';
    tipousuarios = data;
    tipousuarios.forEach((tu) => {
        let datos = '<option value="' + tu.TipoUsuarioId + '">' +
                            tu.Nombre +
                    '</option>';
        cuerpo+=datos;
    });
    document.getElementById("cmbTipoUsuarioId").innerHTML = cuerpo;
}

export function llenarSelectPersona(data){
    let cuerpo = '<option selected="">' +
                    'Seleccione la persona:' +                            
                 '</option>';
    personas = data;
    personas.forEach((p) => {
        let datos = '<option value="' + p.PersonaId + '">' +
                            p.Nombre +
                    '</option>';
        cuerpo+=datos;
    });
    document.getElementById("cmbPersonaId").innerHTML = cuerpo;
}

export function pedirDatos(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    fetch("../../api/usuarios/getAll")
            .then(response => {
                return response.json();
            })
                    .then(data => {
                        if(data.error){
                            Swal.fire('', data.error, 'warning');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else if(data.exception){
                            Swal.fire('', "Error interno del servidor.", 'error');
                            document.querySelector(".spinner-container").classList.add("d-none");
                        } else{
                            llenarTablaUsuarios(data);
                        }
            });
}

export function llenarTablaUsuarios(data){
    let cuerpo = "";
    usuarios = data;
    usuarios.forEach((u) => {
        let datos = '<tr class="text-center" onclick="modulo.seleccionarUsuario('+u.UsuarioId+');">'+
                        '<td>'+u.Persona.Nombre+'</td>'+
                        '<td>'+u.Cuenta+'</td>'+
                        '<td>'+u.Email+'</td>'+
                        '<td>'+(u.Activo === 1 ? "Si" : "No")+'</td>'+
                    '</tr>';
        cuerpo+=datos;
    });
    document.getElementById("tbodyUsuarios").innerHTML = cuerpo;
    document.querySelector(".spinner-container").classList.add("d-none");
}

export async function seleccionarUsuario(UsuarioId){
    await setDetalleVisible(false);
    
    let usuario = usuarios.find(u => u.UsuarioId === UsuarioId);

    UsuarioEditadoId = usuario.UsuarioId;
    document.getElementById("txtCuenta").value = usuario.Cuenta;
    document.getElementById("txtEmail").value = usuario.Email;
    document.getElementById("cmbPersonaId").value = usuario.Persona.PersonaId;
    document.getElementById("cmbTipoUsuarioId").value = usuario.TipoUsuario.TipoUsuarioId;
    document.getElementById("cmbPerfilId").value = usuario.PerfilId;
    document.getElementById("chkActivoUsuario").checked = usuario.Activo === 1 ? true : false;
}

export async function setDetalleVisible(bool){
    if(bool){
        document.getElementById("detalleCatalogodeUsuarios").style.display = "";
        document.getElementById("formularioUsuario").style.display = "none";
        limpiar();
    } else{
        document.getElementById("detalleCatalogodeUsuarios").style.display = "none";
        document.getElementById("formularioUsuario").style.display = "";
    }
}

export function limpiar(){
    UsuarioEditadoId = 0;
    document.getElementById("txtCuenta").value = null;
    document.getElementById("txtEmail").value = null;
    document.getElementById("cmbPersonaId").value = "";
    document.getElementById("cmbTipoUsuarioId").value = "";
    document.getElementById("cmbPerfilId").value = "";
    document.getElementById("chkActivoUsuario").checked = true;
}

export async function guardar(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let datos = null;
    let params = null;
    
    let usuario = new Object();
    usuario.Persona = new Object();
    usuario.TipoUsuario = new Object();
    
    usuario.UsuarioId = UsuarioEditadoId;
    usuario.Persona.PersonaId = 1;// document.getElementById("cmbPersonaId").value;
    usuario.TipoUsuario.TipoUsuarioId = parseInt(document.getElementById("cmbTipoUsuarioId").value);
    usuario.Cuenta = document.getElementById("txtCuenta").value;
    usuario.Contrasena = await encriptar(sanitizar(normalizar(usuario.Cuenta)));
    usuario.Email = document.getElementById("txtEmail").value;
    usuario.Activo = document.getElementById("chkActivoUsuario").checked ? 1 : 0;
    usuario.PerfilId = parseInt(document.getElementById("cmbPerfilId").value);
    
    datos = {
        datosUsuario: JSON.stringify(usuario)
    };
    
    params = new URLSearchParams(datos);
    
    if (UsuarioEditadoId === 0){
        fetch ("../../api/usuarios/guardar", {
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: params
        })
                .then(response=>{
                    return response.json();
                })
                        .then(data => {
                            if(data.exception){
                                Swal.fire('', "Error interno del servidor.", 'error');
                                document.querySelector(".spinner-container").classList.add("d-none");
                            } else{
                                Swal.fire('', "Usuario guardado con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                inicializar();
                            }
                        });
    } else{
        fetch ("../../api/usuarios/actualizar", {
            method: "POST",
            headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
            body: params
        })
                .then(response=>{
                    return response.json();
                })
                        .then(data => {
                            if(data.exception){
                                Swal.fire('', "Error interno del servidor.", 'error');
                                document.querySelector(".spinner-container").classList.add("d-none");
                            } else{
                                Swal.fire('', "Usuario actualizado con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                inicializar();
                            }
                        });
    }
}

export async function eliminar(){
    if (UsuarioEditadoId === 0){
        return;
    }
    
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let datos=null;
    let params=null;
    
    let usuario = new Object();
    
    usuario.UsuarioId = UsuarioEditadoId;
    
    datos = {
        datosUsuario: JSON.stringify(usuario)
    };
    
    params = new URLSearchParams(datos);
    
    fetch ("../../api/usuarios/eliminar", {
            method: "POST",
            headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
            body: params
        })
                .then(response=>{
                    return response.json();
                })
                        .then(data => {
                            if(data.exception){
                                Swal.fire('', "Error interno del servidor.", 'error');
                                document.querySelector(".spinner-container").classList.add("d-none");
                            } else{
                                Swal.fire('', "Usuario eliminado con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                inicializar();
                            }
                        });
}
