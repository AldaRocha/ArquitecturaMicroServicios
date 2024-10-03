$(document).ready(function () {
  cargarPeriodos();

  // Evento para abrir el modal de agregar periodo
  $("#addPeriodoButton").on("click", function () {
    limpiarFormulario(); // Limpiar el formulario
    configurarModal("Agregar Periodo", "Agregar");
    $("#addEditPeriodoModal").modal("show");
  });

  // Evento para agregar o editar un periodo
  $("#formAddEditPeriodo").on("submit", function (e) {
    e.preventDefault();

    const periodoId = $("#periodoId").val();
    const nombre = $("#nombre").val();
    const descripcion = $("#descripcion").val();
    const estatus = $("#estatus").val();
    const tipo = periodoId ? "PUT" : "POST";
    const url = periodoId
      ? `http://localhost:8080/Arquitectura_MicroServicios/api/periodos/${periodoId}`
      : "http://localhost:8080/Arquitectura_MicroServicios/api/periodos";

    $.ajax({
      type: tipo,
      url: url,
      contentType: "application/json",
      data: JSON.stringify({ nombre, descripcion, estatus }),
      success: actualizarListaPeriodos,
      error: function (xhr) {
        alert("Error al procesar el periodo: " + xhr.responseText);
      },
    });
  });

  // Función para cargar los periodos
  function cargarPeriodos() {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/Arquitectura_MicroServicios/api/periodos",
      success: function (periodos) {
        const tbody = $("#tablaPeriodos tbody");
        tbody.empty(); // Limpiar la tabla
        periodos.forEach(function (periodo) {
          tbody.append(`
            <tr>
              <td>${periodo.id}</td>
              <td>${periodo.nombre}</td>
              <td>${periodo.descripcion}</td>
              <td>${periodo.estatus}</td>
              <td>
                <button class="btn btn-warning btn-sm" onclick="mostrarEditarPeriodo(${periodo.id})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="borrarPeriodo(${periodo.id})">Borrar</button>
              </td>
            </tr>
          `);
        });
      },
      error: function (xhr) {
        alert("Error al cargar los periodos: " + xhr.responseText);
      },
    });
  }
});

function mostrarEditarPeriodo(id) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/Arquitectura_MicroServicios/api/periodos/${id}`,
    success: function (periodo) {
      $("#periodoId").val(periodo.id);
      $("#nombre").val(periodo.nombre);
      $("#descripcion").val(periodo.descripcion);
      $("#estatus").val(periodo.estatus);

      // Configurar y mostrar el modal
      configurarModal("Editar Periodo", "Actualizar");
      $("#addEditPeriodoModal").modal("show");
    },
    error: function (xhr) {
      alert("Error al cargar el periodo: " + xhr.responseText);
    },
  });
}

// Función para borrar un periodo
function borrarPeriodo(id) {
  $.ajax({
    type: "DELETE",
    url: `http://localhost:8080/Arquitectura_MicroServicios/api/periodos/${id}`,
    success: cargarPeriodos,
    error: function (xhr) {
      alert("Error al borrar el periodo: " + xhr.responseText);
    },
  });
}

// Función para limpiar el formulario
function limpiarFormulario() {
  $("#periodoId").val("");
  $("#nombre").val("");
  $("#descripcion").val("");
  $("#estatus").val("Abierta");
}

// Función para configurar el modal
function configurarModal(titulo, botonTexto) {
  $("#addEditPeriodoModalLabel").text(titulo);
  $('#formAddEditPeriodo button[type="submit"]').text(botonTexto);
}

// Función para actualizar la lista de periodos y cerrar el modal
function actualizarListaPeriodos() {
  alert("Operación exitosa");
  cargarPeriodos();
  $("#addEditPeriodoModal").modal("hide");
}

// Cerrar el modal en todos los casos
$(".close").on("click", function () {
  $("#addEditPeriodoModal").modal("hide");
});


