/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author criss
 */
public class ProductoServices {

}
//            Path imagePath = Path.of("C:\\Users\\criss\\Downloads\\maxresdefault.jpg");
//            byte[] imageBytes = Files.readAllBytes(imagePath);
//            Producto p = new Producto(imageBytes, "Frijolito", "Cartoon", "Luchador", 1);
//            dao2.persistirEntidad(p); Para Crear la imagen y poder instanciar el producto

//            byte[] imagenBytes = p.getImagen();
////            Usamos ByteArrayInputStream para convertir el byte[] a un flujo de entrada.
//            ByteArrayInputStream bais = new ByteArrayInputStream(imagenBytes);
////            Usamos ImageIO.read para leer la imagen del flujo de entrada y convertirla a un objeto Image.
//            Image imagen = ImageIO.read(bais);
////            Creamos un ImageIcon con el objeto Image.
//            ImageIcon icono = new ImageIcon(imagen);
////            Agregamos la imagen a un Label
//            JLabel label = new JLabel(icono);
////            Por ultimo agregamos la imagen extraida de la base de datos al panel
//            panel.add(label);