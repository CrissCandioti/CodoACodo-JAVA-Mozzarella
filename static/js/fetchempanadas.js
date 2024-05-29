// Fetch
// El objeto opcion carga con características de lo que esperamos recibir
const opcion = {
    method: "GET",
    headers: {
      accept: "application/json",
    },
  };
  
  // Función para crear una tarjeta de empanada
function crearTarjetaEmpanada(empanada) {
  return `
    <div class="col-sm-12 col-md-6 col-lg-4 mt-5">
      <!--Card Responsive-->
      <div class="col mt-2">
        <div class="card" style="width: 18rem">
          <img src="${empanada.imagen}" class="card-img-top" alt="Imagen Empanada" />
          <div class="card-body">
            <h5 class="card-title text-center">${empanada.nombre}</h5>
            <ul>
              <li>Ingredientes: ${empanada.ingredientes}</li>
              <li>Descripción: ${empanada.descripción}</li>
            </ul>
            <p class="d-inline-flex gap-1">
              <a class="btn ms-custom" data-bs-toggle="collapse" href="#collapseExample${empanada.id}" role="button" aria-expanded="false" aria-controls="collapseExample"> Ver más </a>
            </p>
            <div class="collapse" id="collapseExample${empanada.id}">
              <div class="card card-body">
                <div class="d-flex justify-content-around mt-3">
                  <small class="mt-2 ps-3">Costo: ${empanada.precio1} c/u</small>
                  <button type="button" class="btn" onclick='agregarAlCarrito(${JSON.stringify(empanada)}, 1)'><i class="bi bi-cart2"></i></button>
                </div>
                <div class="d-flex justify-content-around mt-3">
                  <small class="mt-2 ps-3">Costo: ${empanada.precio2} x 6u</small>
                  <button type="button" class="btn" onclick='agregarAlCarrito(${JSON.stringify(empanada)}, 6)'><i class="bi bi-cart2"></i></button>
                </div>
                <div class="d-flex justify-content-around mt-3">
                  <small class="mt-2 ps-3">Costo: ${empanada.precio3} x 12u</small>
                  <button type="button" class="btn" onclick='agregarAlCarrito(${JSON.stringify(empanada)}, 12)'><i class="bi bi-cart2"></i></button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `;
}

// Función para cargar los datos de la API
function cargarEmpanada() {
  // Llamamos a nuestro fetch
  fetch("https://rickandmortyapi.com/api/character", opcion)
    .then(function (response) {
      // Transformamos el String (Json) en objeto que Js reconoce
      return response.json();
    })
    .then(function (data) {
      // Manipular objetos del DOM
      const empanadaContainer = document.getElementById("empanada-container");

      // Convertimos los personajes en datos de empanadas simuladas
      const empanadas = data.results.map(function (character, index) {
        return {
          id: index,
          nombre: character.name,
          imagen: character.image,
          ingredientes: "Ingredientes",
          descripción: "Descripción",
          precio1: Math.floor(Math.random() * 1000) + 4000, // Precio simulado entre $4000 y $5000
          precio2: Math.floor(Math.random() * 1000) + 4000, 
          precio3: Math.floor(Math.random() * 1000) + 4000, 
        };
      });

      // Renderizamos las tarjetas de empanadas en el DOM
      empanadaContainer.innerHTML = empanadas.map(crearTarjetaEmpanada).join("");
    })
    .catch(function (error) {
      console.error("Error al cargar las empanadas:", error);
    });
}

// Función para agregar empanada al carrito
function agregarAlCarrito(empanada, cantidad) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  
  // Crear una nueva entrada con la cantidad seleccionada
  const empanadaConCantidad = {
    ...empanada,
    cantidad,
    precioTotal: empanada[`precio${cantidad === 1 ? '1' : cantidad === 6 ? '2' : '3'}`] * cantidad
  };

  carrito.push(empanadaConCantidad);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert(`${empanada.nombre} ha sido añadido al carrito.`);
}

// Llamamos a nuestra función para cargar las empanadas
cargarEmpanada();
