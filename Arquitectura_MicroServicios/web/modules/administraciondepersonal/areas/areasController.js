const API_URL = 'http://localhost:3000/api/areas'; // Cambia esto si es necesario

// Inicializa la aplicación
export function inicializar() {
    fetchAreas();
    document.getElementById('areaForm').addEventListener('submit', saveArea);
}

// Obtener áreas desde la API
async function fetchAreas() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('Error al obtener áreas');
        }
        const areas = await response.json();
        populateAreaTable(areas);
    } catch (error) {
        console.error('Error en fetchAreas:', error);
    }
}

// Llenar la tabla de áreas
function populateAreaTable(areas) {
    const areaTableBody = document.getElementById('areaTable').querySelector('tbody');
    if (!areaTableBody) {
        console.error('No se pudo encontrar el tbody de la tabla de áreas.');
        return; // Salir si no se encuentra el tbody
    }

    areaTableBody.innerHTML = ''; // Limpiar tabla antes de llenarla

    areas.forEach(area => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${area.AreaId}</td>
            <td>${area.Nombre}</td>
            <td>${area.Estatus === 1 ? 'Activo' : 'Inactivo'}</td>
            <td>
                <button class="btn btn-warning" onclick="editArea(${area.AreaId})">Editar</button>
                <button class="btn btn-danger" onclick="deleteArea(${area.AreaId})">Eliminar</button>
            </td>
        `;
        areaTableBody.appendChild(row);
    });
}

// Guardar o actualizar un área
async function saveArea(event) {
    event.preventDefault();

    const areaId = document.getElementById('areaId').value;
    const nombre = document.getElementById('nombre').value;
    const estatus = document.getElementById('estatus').value;

    const method = areaId ? 'PUT' : 'POST';
    const url = areaId ? `${API_URL}/${areaId}` : API_URL;

    const data = { Nombre: nombre, Estatus: estatus };

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        if (!response.ok) {
            throw new Error('Error al guardar el área');
        }
        await fetchAreas(); // Volver a cargar las áreas después de guardar
        resetForm();
    } catch (error) {
        console.error('Error en saveArea:', error);
    }
}

// Editar un área
window.editArea = function(areaId) {
    const areaTableBody = document.getElementById('areaTable').querySelector('tbody');
    const rows = areaTableBody.querySelectorAll('tr');

    rows.forEach(row => {
        const id = row.cells[0].innerText;
        if (id == areaId) {
            document.getElementById('areaId').value = id;
            document.getElementById('nombre').value = row.cells[1].innerText;
            document.getElementById('estatus').value = row.cells[2].innerText === 'Activo' ? '1' : '0';
        }
    });
};

// Eliminar un área
window.deleteArea = async function(areaId) {
    if (confirm('¿Estás seguro de que deseas eliminar esta área?')) {
        try {
            const response = await fetch(`${API_URL}/${areaId}`, { method: 'DELETE' });
            if (!response.ok) {
                throw new Error('Error al eliminar el área');
            }
            await fetchAreas(); // Volver a cargar las áreas después de eliminar
        } catch (error) {
            console.error('Error en deleteArea:', error);
        }
    }
};

// Reiniciar el formulario
function resetForm() {
    document.getElementById('areaId').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('estatus').value = '1'; // Por defecto a Activo
}

// Llamar a la función inicial al cargar la página
document.addEventListener('DOMContentLoaded', inicializar);
