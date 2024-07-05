/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Cliente;
import Persistence.ClienteDAO;
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
@WebServlet(name = "SvCliente", urlPatterns = {"/SvCliente/*"})
public class SvCliente extends HttpServlet {
    
    private ClienteDAO clienteDAO;
    private ObjectMapper objectMapper;
    
    @Override
    public void init() throws ServletException {
        clienteDAO = new ClienteDAO();
        objectMapper = new ObjectMapper();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Cliente> listaClientes = clienteDAO.listaClientes();
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("cliente", listaClientes);
                String json = objectMapper.writeValueAsString(responseMap);
                response.setContentType("application/json");
                response.getWriter().write(json);
            } else {
                String[] pathParts = pathInfo.split("/");
                int id = Integer.parseInt(pathParts[1]);
                Cliente cliente = clienteDAO.buscarClienteId(id);
                if (cliente != null) {
                    String json = objectMapper.writeValueAsString(cliente);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            // Leer el JSON del cuerpo de la solicitud
//            Cliente loginRequest = objectMapper.readValue(request.getReader(), Cliente.class);
//            Cliente cliente = clienteDAO.buscarClientePorEmailYCorreoLogin(loginRequest.getCorreoElectronico(), loginRequest.getContrasena());
//
//            if (cliente != null) {
//                // Si el cliente existe y las credenciales son correctas
//                response.setStatus(HttpServletResponse.SC_OK);
//                response.setContentType("application/json");
//                response.getWriter().write(objectMapper.writeValueAsString(cliente));
//            } else {
//                // Si el cliente no existe o las credenciales son incorrectas
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales incorrectas");
//            }
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor");
//        }
        Cliente cliente = objectMapper.readValue(request.getReader(), Cliente.class);
        clienteDAO.persistirEntidad(cliente);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Cliente cliente = objectMapper.readValue(request.getReader(), Cliente.class);
            Cliente clienteExistente = clienteDAO.buscarClienteId(cliente.getId());
            if (clienteExistente == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado.");
                return;
            }
            clienteDAO.actualizarEntidad(cliente);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            // Manejo de errores con información específica
            e.printStackTrace();
            throw new ServletException("Error al actualizar el cliente: " + e.getMessage(), e);
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo != null && pathInfo.split("/").length > 1) {
                int id = Integer.parseInt(pathInfo.split("/")[1]);
                Cliente cliente = clienteDAO.buscarClienteId(id);
                clienteDAO.borrarEntidad(cliente);
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
