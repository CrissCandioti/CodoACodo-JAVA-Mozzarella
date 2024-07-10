/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.codoacodopizzamozzarella;

import Entidades.Compra;
import Entidades.Producto;
import Persistence.ClienteDAO;
import Persistence.CompraDAO;
import Persistence.ProductoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author criss
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteDAO dao1 = new ClienteDAO();
        ProductoDAO dao2 = new ProductoDAO();
        List<Producto> listaProductosBackend = new ArrayList<>();
        listaProductosBackend.add(dao2.BuscarProductoId(1));
        listaProductosBackend.add(dao2.BuscarProductoId(3));
        Compra compra = new Compra(dao1.buscarClienteId(1), listaProductosBackend, null);
        CompraDAO dao3 = new CompraDAO();
        dao3.persistirEntidad(compra);
    }
}
