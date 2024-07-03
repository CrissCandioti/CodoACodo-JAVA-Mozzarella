// Fetch
// El objeto opcion carga con características de lo que esperamos recibir
const opcion = {
  method: "GET",
  headers: {
    accept: "application/json",
  },
};

// Función para crear una tarjeta de pizza
function crearTarjetaPizza(pizza, index) {
  return `
    <div class="col-sm-12 col-md-6 col-lg-4 mt-5">
      <div class="col mt-2">
        <div class="card" style="width: 18rem">
          <img src="${pizza.imagen}" class="card-img-top" alt="Imagen Pizza" />
          <div class="card-body">
            <h5 class="card-title text-center">${pizza.nombre}</h5>
            <ul>
              <li>Ingredientes: ${pizza.ingredientes}</li>
              <li>Descripción: ${pizza.descripcion}</li>
            </ul>
            <div class="d-flex justify-content-around mt-3">
              <small class="mt-2 ps-3">Costo: $${pizza.precio.toFixed(2)}</small>
              <button type="button" class="btn add-to-cart" data-id="${index}"><i class="bi bi-cart2"></i></button>
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
  fetch("http://localhost:8080/CodoACodoPizzaMozzarella/SvProducto/", opcion)
    .then(function (response) {
      // Transformamos el String (Json) en objeto que Js reconoce
      return response.json();
    })
    .then(function (data) {
      // Manipular objetos del DOM
      const pizzaContainer = document.getElementById("pizza-container");

      // Convertimos los productos en datos de pizzas simuladas
      const pizzas = data.Producto.map(function (producto, index) {
        return {
          id: producto.id,
          nombre: producto.nombre,
          imagen: producto.imagen,
          ingredientes: producto.ingredientes,
          descripcion: producto.descripcion,
          precio: producto.precio, // Utilizamos el campo "precio" del JSON
        };
      });

      // Renderizamos las tarjetas de pizzas en el DOM
      pizzaContainer.innerHTML = pizzas.map(crearTarjetaPizza).join("");

      // Agregar event listeners a los botones de carrito
      document.querySelectorAll(".add-to-cart").forEach(button => {
        button.addEventListener("click", function() {
          const pizzaId = this.getAttribute("data-id");
          agregarAlCarrito(pizzas[pizzaId]);
        });
      });
    })
    .catch(function (error) {
      console.error("Error al cargar las pizzas:", error);
    });
}

// Función para agregar una pizza al carrito
function agregarAlCarrito(pizza) {
  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push(pizza);
  localStorage.setItem("carrito", JSON.stringify(carrito));
  alert(`Pizza ${pizza.nombre} añadida al carrito!`);
}

// Llamamos a nuestra funcion
cargarPizzas();
