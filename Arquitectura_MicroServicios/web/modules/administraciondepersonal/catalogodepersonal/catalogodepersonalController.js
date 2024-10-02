const apiUrl = 'http://localhost:3000/api/personas';
const apiUrl2 = 'http://localhost:3000/api/areas';

// Inicializa la aplicación
export function inicializar() {
    obtenerAreas(); // Llama a la función para cargar las áreas en el select
    fetchPersonal(); // Llama a la función para obtener el personal al iniciar
}

// Fetch para obtener todas las personas
async function fetchPersonal() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error('Error al obtener personal');
        }
        const personal = await response.json();
        renderTable(personal);
    } catch (error) {
        console.error('Error al obtener el personal:', error);
    }
}

// Renderiza la tabla
function renderTable(personal) {
    const tableBody = document.getElementById('personalTableBody');
    tableBody.innerHTML = ''; // Limpiar tabla

    personal.forEach(persona => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${persona.PersonaId}</td>
            <td>${persona.Nombre}</td>
            <td>${persona.Email}</td>
            <td>${persona.Telefono}</td>
            <td>${persona.Email}</td>
   
            
            <td>
                <button class="btn btn-warning" onclick="editPersona(${persona.PersonaId})">Editar</button>
                <button class="btn btn-danger" onclick="deletePersona(${persona.PersonaId})">Eliminar</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Edita una persona
function editPersona(personaId) {
    fetch(`${apiUrl}/${personaId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener persona para editar');
            }
            return response.json();
        })
        .then(persona => {
            // Rellenar el formulario con los datos de la persona
            document.getElementById('personaId').value = persona.PersonaId;
            document.getElementById('Nombre').value = persona.Nombre;
            document.getElementById('Email').value = persona.Email;
            document.getElementById('Telefono').value = persona.Telefono;
            document.getElementById('Curp').value = persona.Curp;
            document.getElementById('NumeroEmpleado').value = persona.NumeroEmpleado;
            document.getElementById('TipoContrato').value = persona.TipoContrato;
            document.getElementById('FechaIngreso').value = new Date(persona.FechaIngreso).toISOString().slice(0, 16); // Formato datetime-local
            document.getElementById('Grupo').value = persona.Grupo;
            document.getElementById('Estatus').value = persona.Estatus;
            document.getElementById('AreaId').value = persona.AreaId;
        })
        .catch(error => {
            console.error('Error al editar la persona:', error);
        });
}

// Elimina una persona
function deletePersona(personaId) {
    fetch(`${apiUrl}/${personaId}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar persona');
            }
            fetchPersonal(); // Refrescar la tabla
        })
        .catch(error => {
            console.error('Error al eliminar la persona:', error);
        });
}

// Guarda una persona (crear o actualizar)
document.getElementById('personalForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevenir el envío del formulario por defecto

    const personaId = document.getElementById('personaId').value;
    const method = personaId ? 'PUT' : 'POST';
    const url = personaId ? `${apiUrl}/${personaId}` : apiUrl;

    const data = {
        Nombre: document.getElementById('Nombre').value,
        Email: document.getElementById('Email').value,
        Telefono: document.getElementById('Telefono').value,
        Curp: document.getElementById('Curp').value,
        NumeroEmpleado: document.getElementById('NumeroEmpleado').value,
        TipoContrato: document.getElementById('TipoContrato').value,
        FechaIngreso: document.getElementById('FechaIngreso').value,
        Grupo: document.getElementById('Grupo').value,
        Estatus: document.getElementById('Estatus').value,
        AreaId: document.getElementById('AreaId').value // Asegúrate de que este ID exista en el formulario
    };

    // Guarda la persona
    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al guardar persona');
        }
        return response.json(); // Asegúrate de que esto devuelva datos, si es necesario
    })
    .then(() => {
        // Si hay otra lógica para guardar áreas, invocala aquí
        // Por ejemplo: guardarArea();
        fetchPersonal(); // Refrescar la tabla
        resetForm(); // Resetear el formulario
    })
    .catch(error => {
        console.error('Error al guardar la persona:', error);
        alert(`Error: ${error.message}`);
    });
});

// Reinicia el formulario
function resetForm() {
    document.getElementById('personalForm').reset();
    document.getElementById('personaId').value = ''; // Limpiar el ID oculto
}

// Función para obtener las áreas
function obtenerAreas() {
    fetch(apiUrl2) // Utiliza la constante apiUrl2
        .then(response => {
            if (!response.ok) throw new Error('Error al obtener áreas');
            return response.json();
        })
        .then(areas => {
            const areaSelect = document.getElementById('AreaId');
            areaSelect.innerHTML = ''; // Limpia las opciones existentes
            areas.forEach(area => {
                const option = document.createElement('option');
                option.value = area.AreaId; // ID del área
                option.textContent = area.Nombre; // Nombre del área
                areaSelect.appendChild(option); // Agrega la opción al select
            });
        })
        .catch(error => {
            console.error('Error al obtener áreas:', error);
            alert('Error al cargar áreas');
        });
}

// Llama a la función para inicializar la aplicación
window.onload = inicializar;

// Asigna las funciones al objeto window para que sean accesibles globalmente
window.editPersona = editPersona;
window.deletePersona = deletePersona;
