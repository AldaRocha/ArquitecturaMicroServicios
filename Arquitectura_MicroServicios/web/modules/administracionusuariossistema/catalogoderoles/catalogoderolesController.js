let roles = [];
let PerfilEditadoId = 0;

export function inicializar(){
    setDetalleVisible(true);
    pedirDatos();
    configureTableFilter(document.getElementById("txtBuscarRol"), document.getElementById("tblRoles"));
}

export function pedirDatos(){
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
                            llenarTablaRoles(data);
                        }
            });
}

export function llenarTablaRoles(data){
    let cuerpo = "";
    roles = data;
    roles.forEach((r) => {
        let datos = '<tr class="text-center" onclick="modulo.seleccionarRol('+r.PerfilId+');">'+
                        '<td>'+r.Nombre+'</td>'+
                        '<td>'+(r.Activo === 1 ? "Si" : "No")+'</td>'+
                    '</tr>';
        cuerpo+=datos;
    });
    document.getElementById("tbodyRoles").innerHTML = cuerpo;
    document.querySelector(".spinner-container").classList.add("d-none");
}

export async function seleccionarRol(PerfilId){
    await setDetalleVisible(false);
    
    let perfil = roles.find(p => p.PerfilId === PerfilId);
    
    PerfilEditadoId = perfil.PerfilId;
    document.getElementById("txtNombreRol").value=perfil.Nombre;
    document.getElementById("chkActivoRol").checked = perfil.Activo === 1 ? true : false;
}

export async function setDetalleVisible(bool){
    if(bool){
        document.getElementById("detalleCatalogodeRoles").style.display = "";
        document.getElementById("formularioRol").style.display = "none";
        limpiar();
    } else{
        document.getElementById("detalleCatalogodeRoles").style.display = "none";
        document.getElementById("formularioRol").style.display = "";
    }
}

export function limpiar(){
    PerfilEditadoId = 0;
    document.getElementById("txtNombreRol").value = null;
    document.getElementById("chkActivoRol").checked = true;
}

export async function guardar(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let datos=null;
    let params=null;
    
    let perfil = new Object();
    
    perfil.PerfilId = PerfilEditadoId;
    perfil.Nombre = document.getElementById("txtNombreRol").value;
    perfil.Activo = document.getElementById("chkActivoRol").checked ? 1 : 0;
    
    datos = {
        datosPerfil: JSON.stringify(perfil)
    };
    
    params = new URLSearchParams(datos);
    
    if (PerfilEditadoId === 0){
        fetch ("../../api/roles/guardar", {
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
                                Swal.fire('', "Rol guardado con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                inicializar();
                            }
                        });
    } else{
        fetch ("../../api/roles/actualizar", {
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
                                Swal.fire('', "Rol actualizado con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                inicializar();
                            }
                        });
    }
}

export async function eliminar(){
    if (PerfilEditadoId === 0){
        return;
    }
    
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let datos=null;
    let params=null;
    
    let perfil = new Object();
    
    perfil.PerfilId = PerfilEditadoId;
    
    datos = {
        datosPerfil: JSON.stringify(perfil)
    };
    
    params = new URLSearchParams(datos);
    
    fetch ("../../api/roles/eliminar", {
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
                                Swal.fire('', "Rol eliminado con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                inicializar();
                            }
                        });
}
