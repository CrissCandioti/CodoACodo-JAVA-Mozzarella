/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Compra;
import Persistence.CompraDAO;
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
@WebServlet(name = "SvCompra", urlPatterns = {"/SvCompra"})
public class SvCompra extends HttpServlet {

    private CompraDAO compraDAO;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        compraDAO = new CompraDAO();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Compra> listaCompras = compraDAO.listaCompras();
                String json = objectMapper.writeValueAsString(listaCompras);
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
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Compra compra = objectMapper.readValue(request.getReader(), Compra.class);
        compraDAO.persistirEntidad(compra);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
