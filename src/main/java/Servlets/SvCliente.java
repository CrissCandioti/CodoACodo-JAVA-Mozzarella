/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Cliente;
import Persistence.ClienteDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Cliente> listaClientes = clienteDAO.listaClientes();
                String json = objectMapper.writeValueAsString(listaClientes);
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
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
