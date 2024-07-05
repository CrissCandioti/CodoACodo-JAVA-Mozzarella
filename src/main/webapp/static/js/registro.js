document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerForm');
    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const nombre = document.getElementById('floatingInputNombre').value;
        const apellido = document.getElementById('floatingInputApellido').value;
        const nombreDeUsuario = document.getElementById('floatingInputNombreUsuario').value;
        const correoElectronico = document.getElementById('floatingInputCorreoElectronico').value;
        const contrasena = document.getElementById('floatingPassword').value;

        try {
            const response = await fetch('http://localhost:8080/CodoACodoPizzaMozzarella/SvCliente/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    nombre: nombre,
                    apellido: apellido,
                    nombreUsuario: nombreDeUsuario,
                    correoElectronico: correoElectronico,
                    contrasena: contrasena,
                    comengarios: null
                })
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Resultado:', result);
                alert('Registro exitoso');
            } else {
                const errorText = await response.text();
                console.error('Error en el servidor:', errorText);
                alert('Registro fallido');
            }
        } catch (error) {
            console.error('Error de red o de fetch:', error);
            alert('Error en el registro');
        }
    });
});
