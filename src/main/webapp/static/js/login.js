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
                body: JSON.stringify({correoElectronico: email, contrasena: password})
            });
            localStorage.setItem('usuario', email);
            console.log(localStorage.getItem('usuario'));
            alert('Se inicio correctamente la sesion :)');
            window.location.href = '../index.html';
        } catch (error) {
            console.error('Error en la solicitud de inicio de sesión:', error);
            alert('Error en la solicitud de inicio de sesión');
        }
    });
});
