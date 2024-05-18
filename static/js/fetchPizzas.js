// Fetch
// El objeto opcion carga con características de lo que esperamos recibir
const opcion = {
  method: "GET",
  headers: {
    accept: "application/json",
  },
};

// Función para crear una tarjeta de pizza
function crearTarjetaPizza(pizza) {
  return `
    <div class="col-sm-12 col-md-6 col-lg-4 mt-5">
      <div class="col mt-2">
        <div class="card" style="width: 18rem">
          <img src="${pizza.imagen}" class="card-img-top" alt="Imagen Pizza" />
          <div class="card-body">
            <h5 class="card-title text-center">${pizza.nombre}</h5>
            <ul>
              <li>Ingredientes: ${pizza.ingredientes}</li>
              <li>Descripción: ${pizza.descripción}</li>
            </ul>
            <div class="d-flex justify-content-around mt-3">
              <small class="mt-2 ps-3">Costo: $${pizza.price}</small>
              <button type="button" class="btn"><i class="bi bi-cart2"></i></button>
            </div>
          </div>
        </div>
      </div>
    </div>
  `;
}

// Función para cargar los datos de la API
function cargarPizzas() {
  // Llamamos a nuestro fetch
  fetch("https://rickandmortyapi.com/api/character", opcion)
    .then(function (response) {
      // Transformamos el String (Json) en objeto que Js reconoce
      return response.json();
    })
    .then(function (data) {
      // Manipular objetos del DOM
      const pizzaContainer = document.getElementById("pizza-container");

      // Convertimos los personajes en datos de pizzas simuladas
      const pizzas = data.results.map(function (character) {
        return {
          nombre: character.name,
          imagen: character.image,
          ingredientes: "Ingredientes",
          descripción: "Descripción",
          price: Math.floor(Math.random() * 1000) + 4000, // Precio simulado entre $4000 y $5000
        };
      });

      // Renderizamos las tarjetas de pizzas en el DOM
      pizzaContainer.innerHTML = pizzas.map(crearTarjetaPizza).join("");
    })
    .catch(function (error) {
      console.error("Error al cargar las pizzas:", error);
    });
}

// Escuchar el evento DOMContentLoaded para cargar las pizzas cuando la página esté lista
document.addEventListener("DOMContentLoaded", function () {
  cargarPizzas();
});
