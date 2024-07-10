/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Cliente;
import Entidades.Compra;
import Entidades.Producto;
import Persistence.ClienteDAO;
import Persistence.CompraDAO;
import Persistence.ProductoDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author criss
 */
@WebServlet(name = "SvCompra", urlPatterns = {"/SvCompra/*"})
public class SvCompra extends HttpServlet {

    private CompraDAO compraDAO;
    private ObjectMapper objectMapper;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
        compraDAO = new CompraDAO();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Compra> listaCompra = compraDAO.listaCompras();
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("compra", listaCompra);
                String json = objectMapper.writeValueAsString(responseMap);
                response.setContentType("application/json");
                response.getWriter().write(json);
            } else {
                String[] pathParts = pathInfo.split("/");
                int id = Integer.parseInt(pathParts[1]);
                Compra compra = compraDAO.buscarCompraId(id);
                if (compra != null) {
                    String json = objectMapper.writeValueAsString(compra);
                    response.setContentType("application/json");
                    response.getWriter().write(json);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Compra compraFront = objectMapper.readValue(request.getReader(), Compra.class);
            List<Producto> listaProductosBackend = new ArrayList<>();
            List<Producto> listaProductosFront = compraFront.getProductos();
            for (Producto producto : listaProductosFront) {
                listaProductosBackend.add(productoDAO.BuscarProductoId(producto.getId()));
            }
            Cliente cliente = clienteDAO.buscarCLientePorEmail(compraFront.getCliente().getCorreoElectronico());
            Compra compraBaseDatos = new Compra(cliente, listaProductosFront, null);
            compraDAO.persistirEntidad(compraBaseDatos);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setContentType("application/json");
        } catch (Exception e) {
            // Manejo de errores con información específica
            e.printStackTrace();
            throw new ServletException("Error al actualizar la compra: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Compra compra = objectMapper.readValue(request.getReader(), Compra.class);
            Compra compraExistente = compraDAO.buscarCompraId(compra.getId());
            if (compraExistente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Compra no encontrado.");
                return;
            }
            compraDAO.actualizarEntidad(compra);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            // Manejo de errores con información específica
            e.printStackTrace();
            throw new ServletException("Error al actualizar la compra: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo != null && pathInfo.split("/").length > 1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                Compra compra = compraDAO.buscarCompraId(id);
                compraDAO.borrarEntidad(compra);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
