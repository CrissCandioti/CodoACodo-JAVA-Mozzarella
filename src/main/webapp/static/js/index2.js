document.addEventListener('DOMContentLoaded', () => {
    const comentariosContainer = document.getElementById('comentarios-container');

    // Obtener los datos de la API
    fetch('http://localhost:8080/CodoACodoPizzaMozzarella/SvCliente/')
        .then(response => response.json())
        .then(data => {
            const clientes = data.cliente;

            if (clientes.length === 0) {
                comentariosContainer.innerHTML = '<p>No hay comentarios disponibles.</p>';
                return;
            }

            // Limpiar el contenedor antes de agregar nuevos comentarios
            comentariosContainer.innerHTML = '';

            // Crear las tarjetas de comentario para cada cliente
            clientes.forEach((cliente, index) => {
                const isActive = index === 0 ? 'active' : '';

                // Crear el HTML para cada comentario
                const comentarioHTML = `
                    <div class="carousel-item ${isActive}">
                        <div class="card" style="height: 200px">
                            <div class="card-header">Comentario de ${cliente.nombre} ${cliente.apellido}</div>
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p>${cliente.comengarios}</p>
                                    <footer class="blockquote-footer">${cliente.nombre} ${cliente.apellido}</footer>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                `;

                comentariosContainer.innerHTML += comentarioHTML;
            });
        })
        .catch(error => {
            console.error('Error al obtener los comentarios:', error);
            comentariosContainer.innerHTML = '<p>Error al cargar los comentarios.</p>';
        });
});
