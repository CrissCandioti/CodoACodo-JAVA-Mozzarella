/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Producto;
import Persistence.ProductoDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
@WebServlet(name = "SvProducto", urlPatterns = {"/SvProducto/*"})
public class SvProducto extends HttpServlet {

    private ProductoDAO productoDAO;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        productoDAO = new ProductoDAO();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Producto> listaProductos = productoDAO.listaProductos();
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("Producto", listaProductos);
                String json = objectMapper.writeValueAsString(responseMap);
                response.setContentType("application/json");
                response.getWriter().write(json);
            } else {
                String[] pathParts = pathInfo.split("/");
                int id = Integer.parseInt(pathParts[1]);
                Producto producto = productoDAO.BuscarProductoId(id);
                if (producto != null) {
                    String json = objectMapper.writeValueAsString(producto);
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
        Producto producto = objectMapper.readValue(request.getReader(), Producto.class);
        productoDAO.persistirEntidad(producto);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Producto producto = objectMapper.readValue(request.getReader(), Producto.class);
            Producto productoExistente = productoDAO.BuscarProductoId(producto.getId());
            if (productoExistente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado.");
                return;
            }
            productoDAO.actualizarEntidad(producto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            // Manejo de errores con información específica
            e.printStackTrace();
            throw new ServletException("Error al actualizar el producto: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo != null && pathInfo.split("/").length > 1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                Producto producto = productoDAO.BuscarProductoId(id);
                productoDAO.borrarEntidad(producto);
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
        return "Servlet para manejar productos.";
    }

}
