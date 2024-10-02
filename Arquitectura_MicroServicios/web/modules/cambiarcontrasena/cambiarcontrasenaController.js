
export function inicializar(){
    
}

export async function cambiarContrasenia(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let contrasenia = sanitizar(document.getElementById("txtContraseniaNueva").value);
    let confirmarContrasenia = sanitizar(document.getElementById("txtConfirmarContraseniaNueva").value);
    
    if (contrasenia === confirmarContrasenia){
        let datos = null;
        let params = null;
        
        let usuario = new Object();
        
        let currentUser = localStorage.getItem('currentUser');
        let u = JSON.parse(currentUser);
        
        usuario.UsuarioId = u.UsuarioId;
        let contraseniaEncriptada = await encriptar(confirmarContrasenia);
        usuario.Contrasena = contraseniaEncriptada;
        
        datos={
                datosUsuario: JSON.stringify(usuario)
        };
        
        params=new URLSearchParams(datos);
        
        fetch("../../api/usuarios/cambiarContrasenia",{
            method: "POST",
            headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
            body: params
        })
                .then(response=>{
                    return response.json();
                })
                        .then(data=>{
                            if(data.error){
                                Swal.fire('', data.error, 'warning');
                                document.querySelector(".spinner-container").classList.add("d-none");
                            } else if(data.exception){
                                Swal.fire('', "Error interno del servidor.", 'error');
                                document.querySelector(".spinner-container").classList.add("d-none");
                            } else{
                                Swal.fire('', "La contraseña se cambio con exito", 'success');
                                document.querySelector(".spinner-container").classList.add("d-none");
                            }
                        });
    } else{
        Swal.fire('', 'Las contraseñas no coinciden', 'warning');
        document.querySelector(".spinner-container").classList.add("d-none");
    }
}
