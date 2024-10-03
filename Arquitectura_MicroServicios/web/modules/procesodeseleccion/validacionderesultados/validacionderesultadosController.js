$(document).ready(function () {
  cargarAspirantes();

  function cargarAspirantes() {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/Arquitectura_MicroServicios/api/aspirantes",
      success: function (aspirantes) {
        const tbody = $("#tablaAspirantes tbody");
        tbody.empty();
        aspirantes.forEach(function (aspirante) {
          // Determinar la clase según el estatus
          let estatusClass = "";
          switch (aspirante.estatus) {
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
                        <td>${aspirante.id}</td>
                        <td>${aspirante.nombre}</td>
                        <td>${aspirante.correo}</td>
                        <td>${aspirante.telefono}</td>
                        <td>${aspirante.usuario}</td>
                        <td>${aspirante.curp}</td>
                        <td>${aspirante.periodoNombre}</td>
                        <td>${aspirante.carreraNombre}</td>
                        <td class="${estatusClass}">${aspirante.estatus}</td>
                        <td>
                            <button class="btn btn-success" onclick="confirmarCambioEstatus(${aspirante.id}, 'aceptado')">Aceptar</button>
                            <button class="btn btn-danger" onclick="confirmarCambioEstatus(${aspirante.id}, 'rechazado')">Rechazar</button>
                        </td>
                    </tr>
                `);
        });
      },
      error: function (xhr) {
        alert("Error al cargar aspirantes: " + xhr.responseText);
      },
    });
  }

  window.confirmarCambioEstatus = function (id, estatus) {
    const mensaje = `¿Estás seguro de ${estatus === "aceptado" ? "aceptar" : "rechazar"} al aspirante con ID ${id}?`;
    if (confirm(mensaje)) {
      cambiarEstatus(id, estatus);
    }
  };

  function cambiarEstatus(id, estatus) {
    $.ajax({
      type: "PUT",
      url: `http://localhost:8080/Arquitectura_MicroServicios/api/aspirantes/${id}/estatus`,
      contentType: "application/json",
      data: JSON.stringify({ estatus }),
      success: function () {
        alert("Estatus actualizado");
        cargarAspirantes(); // Recargar la lista de aspirantes
      },
      error: function (xhr) {
        alert("Error al cambiar el estatus: " + xhr.responseText);
      },
    });
  }
});
