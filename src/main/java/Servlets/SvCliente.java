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
@WebServlet(name = "SvCliente", urlPatterns = {"/SvCliente"})
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = objectMapper.readValue(request.getReader(), Cliente.class);
        clienteDAO.persistirEntidad(cliente);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente cliente = objectMapper.readValue(request.getReader(), Cliente.class);
            clienteDAO.actualizarEntidad(cliente);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            throw new ServletException(e);
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
