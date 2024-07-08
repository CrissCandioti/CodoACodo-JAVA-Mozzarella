document.addEventListener('DOMContentLoaded', () => {
    const contactoForm = document.getElementById('contactoForm');
    contactoForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const nombre = document.getElementById('floatingInputNombre').value;
        const apellido = document.getElementById('floatingInputApellido').value;
        const correoElectronico = document.getElementById('floatingInputCorreoElectronico').value;
        const comentario = document.getElementById('FormControlTextarea').value;

        try {
            const response = await fetch('http://localhost:8080/CodoACodoPizzaMozzarella/SvCliente/', {
                method: 'PUT',
                header: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    comengarios: comentario
                })
            });
            alert('Se agrego el comentario en nuestra base de datos');
        } catch (error) {
            console.error('Error de red o de fetch', error);
            alert('Error en el registro', error);
        }
    });





















})

