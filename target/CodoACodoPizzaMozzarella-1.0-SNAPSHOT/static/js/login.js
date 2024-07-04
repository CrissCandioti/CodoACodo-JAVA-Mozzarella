// login.js

document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm');

  loginForm.addEventListener('submit', async (event) => {
    event.preventDefault(); // Prevenir el envío del formulario por defecto

    // Obtener los valores del formulario
    const email = document.getElementById('floatingInput').value;
    const password = document.getElementById('floatingPassword').value;

    try {
      // Realizar la solicitud POST al servlet
      const response = await fetch('http://localhost:8080/CodoACodoPizzaMozzarella/SvCliente/', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ correoElectronico: email, contrasena: password })
      });

      if (response.ok) {
        const result = await response.json();
        // Si el inicio de sesión es exitoso, puedes redirigir al usuario o mostrar un mensaje
        alert('Inicio de sesión exitoso');
        // Redirigir a la página de inicio
        window.location.href = '../index.html';
      } else {
        // Manejar el caso en que la solicitud falla
        alert('Credenciales incorrectas');
      }
    } catch (error) {
      console.error('Error en la solicitud de inicio de sesión:', error);
      alert('Error en la solicitud de inicio de sesión');
    }
  });
});
