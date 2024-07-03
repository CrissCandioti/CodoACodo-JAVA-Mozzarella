    // Función para crear una tarjeta de pizza del carrito
    function crearTarjetaCarritoCompra(pizza, index) {
      return `
        <div class="col-sm-12 col-md-6 col-lg-4 mt-5" data-index="${index}">
          <div class="card" style="width: 18rem;">
            <img src="${pizza.imagen}" class="card-img-top" alt="Imagen Pizza">
            <div class="card-body">
              <h5 class="card-title text-center">${pizza.nombre}</h5>
              <ul>
                <li>Ingredientes: ${pizza.ingredientes}</li>
                <li>Descripción: ${pizza.descripción}</li>
              </ul>
              <div class="d-flex justify-content-around mt-3">
                <small class="mt-2 ps-3">Costo: $${pizza.price}</small>
                <button class="btn btn-danger" onclick="quitarDelCarrito(${index})">Quitar</button>
              </div>
            </div>
          </div>
        </div>
      `;
    }

    // Función para cargar los elementos del carrito desde localStorage
    function cargarCarrito() {
      const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
      const carritoContainer = document.getElementById("carrito-container");

      if (carrito.length === 0) {
        carritoContainer.innerHTML = "<p class='text-light'>Tu carrito está vacío</p>";
      } else {
        carritoContainer.innerHTML = carrito.map((pizza, index) => crearTarjetaCarritoCompra(pizza, index)).join("");
      }
      actualizarPrecioTotal(); // Actualizar el precio total al cargar el carrito
    }

    // Función para quitar un elemento del carrito
    function quitarDelCarrito(index) {
      let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
      carrito.splice(index, 1); // Eliminar el elemento del array
      localStorage.setItem("carrito", JSON.stringify(carrito)); // Actualizar localStorage
      cargarCarrito(); // Volver a cargar el carrito en la página
    }

    // Función para calcular y actualizar el precio total del carrito
    function actualizarPrecioTotal() {
      const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
      const precioTotal = carrito.reduce((total, pizza) => total + pizza.price, 0);
      document.getElementById("precio-total").innerText = `Precio Total: $${precioTotal}`;
    }

    // Llamamos a nuestra función para cargar el carrito
    cargarCarrito();