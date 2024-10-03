$(document).ready(function () {
    cargarCarreras();
    cargarPeriodosAbiertos();
    
    document.getElementById('btnVolver').addEventListener('click', function() {
        window.location.href = '../../../../index.html'; 
    });
    

    // Función para cargar las carreras
    function cargarCarreras() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/Arquitectura_MicroServicios/api/carreras",
            success: function (carreras) {
                const selectCarrera = $("#carreraDeseada");
                selectCarrera.empty(); // Limpiar el dropdown
                carreras.forEach(function (carrera) {
                    selectCarrera.append(`<option value="${carrera.id}">${carrera.nombre}</option>`);
                });
            },
            error: function (xhr) {
                alert("Error al cargar las carreras: " + xhr.responseText);
            },
        });
    }

    // Función para cargar los periodos abiertos
    function cargarPeriodosAbiertos() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/Arquitectura_MicroServicios/api/periodos/abiertos",
            success: function (periodos) {
                const selectPeriodo = $("#periodoId");
                selectPeriodo.empty(); // Limpiar el dropdown
                periodos.forEach(function (periodo) {
                    selectPeriodo.append(`<option value="${periodo.id}">${periodo.nombre}</option>`);
                });
            },
            error: function (xhr) {
                alert("Error al cargar los periodos: " + xhr.responseText);
            },
        });
    }

    // Manejo del formulario de registro de aspirantes
    $('#registroAspiranteForm').on('submit', function (e) {
        e.preventDefault(); // Evitar el envío normal del formulario

        // Obtén los datos del formulario
        const nombre = $('#nombre').val();
        const correo = $('#correo').val();
        const telefono = $('#telefono').val();
        const usuario = $('#usuario').val();
        const curp = $('#curp').val();
        const carreraDeseada = $('#carreraDeseada').val();
        const periodoId = $('#periodoId').val();

        if (!carreraDeseada || !periodoId) {
            alert('Selecciona una carrera y un periodo antes de enviar.');
            return;
        }

        // Crear el objeto aspirante
        const aspirante = {
            nombre,
            correo,
            telefono,
            usuario,
            curp,
            periodoId: parseInt(periodoId, 10),
            carreraId: parseInt(carreraDeseada, 10),
        };

        // Envía los datos al servidor
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/Arquitectura_MicroServicios/api/aspirantes',
            contentType: 'application/json',
            data: JSON.stringify(aspirante),
            success: function (response) {
                const aspiranteId = response.id; // Obtener el ID de la respuesta
                alert(`Registro exitoso. Tu ID de registro es: ${aspiranteId}`);
                $('#registroAspiranteForm')[0].reset(); // Limpiar el formulario
            },
            error: function (xhr) {
                alert('Error al registrar el aspirante: ' + xhr.responseText);
            }
        });
    });
});
