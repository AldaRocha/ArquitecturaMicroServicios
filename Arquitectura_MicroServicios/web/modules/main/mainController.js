let permisos=[];
let permisosLimpios = [];
let modulos = [];
let modulo;

function inicializar(){
    pedirLinks();
}

function pedirLinks(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let u=localStorage.getItem('currentUser');
    let usuario={"usuario": u};
    let params=new URLSearchParams(usuario);
    
    fetch("../../api/log/pantallas", {
        method: "POST",
        headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
        body: params
    })
            .then(response => {
                return response.json();
            })
                    .then(data => {
                        if (data.error){
                            Swal.fire('', data.error, 'warning');
                            document.querySelector(".spinner-container").classList.add("d-none");
                            let cuerpoDropdownMenu=`<li>
                                                        <a class="dropdown-item title navHover" onclick="cerrarSesion();">
                                                            Cerrar Sesión
                                                        </a>
                                                    </li>`;                            
                            document.getElementById("dropdownMenu").innerHTML = cuerpoDropdownMenu;
                        } else if (data.exception){
                            Swal.fire('', "Error interno del servidor.", 'error');
                            document.querySelector(".spinner-container").classList.add("d-none");
                            let cuerpoDropdownMenu=`<li>
                                                        <a class="dropdown-item title navHover" onclick="cerrarSesion();">
                                                            Cerrar Sesión
                                                        </a>
                                                    </li>`;                            
                            document.getElementById("dropdownMenu").innerHTML = cuerpoDropdownMenu;
                        } else{
                            llenarPantallaMain(data);
                        }
                    });
}

function llenarPantallaMain(data){
    let cuerpoNormales = "";
    let cuerpoDropdownMenu = "";
    
    permisos = data;

    let u = localStorage.getItem('currentUser');
    let usuario = JSON.parse(u);
    let nombreUsuarioLoggeado = usuario.Persona.Nombre;
    
    document.getElementById("nombreUsuarioSession").innerHTML = nombreUsuarioLoggeado;
    
    let agregar;
    let ultimoAgregado = "";
    permisos.forEach((p) => {
        if (p.Identificador === ultimoAgregado){
            agregar = false;
        } else{
            agregar = true;
        }
        
        if (agregar){
            ultimoAgregado = p.Identificador;
            permisosLimpios.push(p);
        }
    });
    
    ultimoAgregado = "";
    
    permisosLimpios.forEach((p) => {
        if (p.Modulo.Identificador === ultimoAgregado){
            agregar = false;
        } else{
            agregar = true;
        }
        
        if (agregar){
            ultimoAgregado = p.Modulo.Identificador;
            modulos.push(p.Modulo);
        }
    });
    
    modulos.forEach((m) => {
        let pantallas = permisosLimpios.filter(p => p.Modulo.ModuloId === m.ModuloId);
        let dropdowns = "";
        pantallas.forEach((p) => {
            dropdowns += `<li class="nav-item navHover mx-2">
                            <a class="nav-link title navHover" onclick="pantalla(` + p.PantallaId + `);">
                                `+p.Nombre+`
                            </a>
                         </li>`;
        });
        
        cuerpoNormales += `<li class="nav-item dropdown navHover mx-3">
                                <a class="nav-link dropdown-toggle title navHover" href="" role="button" id="` + m.Identificador + `" data-bs-toggle="dropdown">` + m.Nombre + `</a>
                                    <ul class="dropdown-menu" id="dropdownMenu` + m.Identificador + `">` +
                                    dropdowns +
                                `</ul>
                           </li>`;
    });
    
    let registroCerrarSesion = `<li>
                                    <a class="dropdown-item title navHover" onclick="cerrarSesion();">
                                        Cerrar Sesión
                                    </a>
                                </li>`;
    
    cuerpoDropdownMenu += registroCerrarSesion;
    
    document.getElementById("normales").innerHTML=cuerpoNormales;
    document.getElementById("dropdownMenu").innerHTML = cuerpoDropdownMenu;
    
    document.querySelector(".spinner-container").classList.add("d-none");
}

function pantalla(pantallaId){
    let pantallaSeleccionada = permisosLimpios.find(p => p.PantallaId === pantallaId);
    
    let ruta = pantallaSeleccionada.Ruta;
    let js = ruta.split("/");
    document.getElementById("contenedorPrincipal").classList.remove("tamanio");
    
    fetch(ruta)
            .then(function(response){
                return response.text();
            })
                    .then(function(html){
                        document.getElementById("contenedorPrincipal").innerHTML=html;
                        import("../"+js[1]+"/"+js[2]+"/"+js[2]+"Controller.js").then(function(controller){
                            modulo=controller;
                            //modulo.inicializar();
                        });
                    });
}

function cerrarSesion(){
    document.querySelector(".spinner-container").classList.remove("d-none");
    
    let u = localStorage.getItem('currentUser');
    if(u !== null){
        let usuario = {"usuario": u};
        let params=new URLSearchParams(usuario);
    
        fetch("../../api/log/out",{
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
                                Swal.fire('', "Sesion cerrada con exito", 'success');
                                localStorage.removeItem('currentUser');
                                document.querySelector(".spinner-container").classList.add("d-none");
                                window.location.replace("../../index.html");
                            }
                        });
    } else{        
        document.querySelector(".spinner-container").classList.add("d-none");
        window.location.replace("../../index.html");
    }
}
