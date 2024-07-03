/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.codoacodopizzamozzarella;

import Entidades.Cliente;
import Entidades.Producto;
import Persistence.ClienteDAO;
import Persistence.ProductoDAO;

/**
 *
 * @author criss
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cliente c = new Cliente("Criss", "Candioi", "CrissC30", "criss@hotmail.com", "3312", "Esta buenarda la pizza de peperoni");
        ClienteDAO dao = new ClienteDAO();
        dao.persistirEntidad(c);
        System.out.println("Se agrego correctamente");
    }
}
