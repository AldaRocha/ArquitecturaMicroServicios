$(document).ready(function () {
    cargarResultados();

    document.getElementById("btnVolver").addEventListener("click", function () {
        window.location.href = "../../../../index.html";
    });

    function cargarResultados() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/Arquitectura_MicroServicios/api/aspirantes/resultados",
            success: function (resultados) {
                const tbody = $("#tablaResultados tbody");
                tbody.empty();
                resultados.forEach(function (resultado) {
                    let estatusClass = "";
                    switch (resultado.estatus) {
                        case "aceptado":
                            estatusClass = "aceptado";
                            break;
                        case "rechazado":
                            estatusClass = "rechazado";
                            break;
                        case "registrado":
                            estatusClass = "registrado";
                            break;
                        default:
                            estatusClass = "";
                    }

                    tbody.append(`
                        <tr>
                            <td>${resultado.id}</td>
                            <td>${resultado.nombre}</td>
                            <td>${resultado.correo}</td>
                            <td>${resultado.telefono}</td>
                            <td>${resultado.usuario}</td>
                            <td>${resultado.curp}</td>
                            <td>${resultado.carreraNombre}</td>
                            <td>${resultado.periodoNombre}</td>
                            <td class="${estatusClass}">${resultado.estatus}</td>
                        </tr>
                    `);
                });
            },
            error: function (xhr) {
                alert("Error al cargar resultados: " + xhr.responseText);
            },
        });
    }

    $("#btnBuscar").on("click", function () {
        const id = $("#idBuscar").val();

        if (!id) {
            alert("Por favor, ingresa un ID válido.");
            return;
        }

        $.ajax({
            type: "GET",
            url: `http://localhost:8080/Arquitectura_MicroServicios/api/aspirantes/resultados/${id}`,
            success: function (resultado) {
                console.log(resultado);
                if (resultado) {
                    const tbody = $("#tablaResultados tbody");
                    tbody.empty();

                    // Asignar la clase de estatus
                    let estatusClass = "";
                    switch (resultado.estatus) {
                        case "aceptado":
                            estatusClass = "aceptado";
                            break;
                        case "rechazado":
                            estatusClass = "rechazado";
                            break;
                        case "registrado":
                            estatusClass = "registrado";
                            break;
                        default:
                            estatusClass = "";
                    }

                    tbody.append(`
                        <tr>
                            <td>${resultado.id}</td>
                            <td>${resultado.nombre}</td>
                            <td>${resultado.correo}</td>
                            <td>${resultado.telefono}</td>
                            <td>${resultado.usuario}</td>
                            <td>${resultado.curp}</td>
                            <td>${resultado.carreraNombre}</td>
                            <td>${resultado.periodoNombre}</td>
                            <td class="${estatusClass}">${resultado.estatus}</td>
                        </tr>
                    `);
                } else {
                    alert("No se encontró el resultado.");
                }
            },
            error: function (xhr) {
                alert("Error al buscar resultado: " + xhr.responseText);
            },
        });
    });
});
