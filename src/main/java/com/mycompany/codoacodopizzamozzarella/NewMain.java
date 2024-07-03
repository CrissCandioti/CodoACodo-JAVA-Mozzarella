/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.codoacodopizzamozzarella;

import Persistence.ClienteDAO;

/**
 *
 * @author criss
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
        System.out.println(dao.buscarCLientePorEmail("criss@hotmail.com"));
    }
}
