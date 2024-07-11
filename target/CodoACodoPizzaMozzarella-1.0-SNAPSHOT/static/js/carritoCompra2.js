async function guardarCarritoEnBaseDeDatos() {
    const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
    const usuario = localStorage.getItem("usuario") || ''; // Obtener el usuario del localStorage

    try {
        const response = await fetch('http://localhost:8080/CodoACodoPizzaMozzarella/SvCompra', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({usuario: usuario, productos: carrito, direccion: null})
        });
        alert('¡La compra se guardó correctamente!');
        localStorage.removeItem("carrito");
        window.location.href = '../index.html';
    } catch (error) {
        console.error('Error:', error);
        alert('Error al guardar la compra');
    }
}

document.getElementById('guardarCarritoBtn').addEventListener('click', function () {
    guardarCarritoEnBaseDeDatos();
});