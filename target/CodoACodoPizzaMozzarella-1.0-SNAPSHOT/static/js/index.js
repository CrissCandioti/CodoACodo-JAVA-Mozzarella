  // URL de la API
  const apiUrl = 'http://localhost:8080/CodoACodoPizzaMozzarella/SvProducto/';

  // Función para obtener las pizzas desde la API
  async function fetchPizzas() {
    try {
      const response = await fetch(apiUrl);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      return data.Producto;
    } catch (error) {
      console.error('Error fetching pizzas:', error);
      return [];
    }
  }

  // Función para crear una tarjeta de pizza
  function createPizzaCard(pizza) {
    return `
      <div class="col-sm-12 col-md-6 col-lg-4" data-aos="fade-up">
        <div class="card" style="width: 18rem; height: 450px">
          <img src="${pizza.imagen}" class="card-img-top" alt="Imagen Pizza" />
          <div class="card-body">
            <h5 class="card-title text-center">${pizza.nombre}</h5>
            <ul>
              <li>Ingredientes: ${pizza.ingredientes}</li>
              <li>Descripción: ${pizza.descripcion}</li>
              <li>Precio: $${pizza.precio.toFixed(2)}</li>
            </ul>
          </div>
        </div>
      </div>
    `;
  }

  // Función para cargar las pizzas en el contenedor
  async function loadPizzas() {
    const pizzas = await fetchPizzas();
    const pizzaContainer = document.getElementById('pizza-container');
    
    // Limita a 3 productos
    const pizzasToShow = pizzas.slice(0, 3);

    // Crea el HTML para las tarjetas de pizza
    pizzaContainer.innerHTML = pizzasToShow.map(createPizzaCard).join('');
  }

  // Llama a la función para cargar las pizzas cuando el contenido de la página esté listo
  document.addEventListener('DOMContentLoaded', loadPizzas);