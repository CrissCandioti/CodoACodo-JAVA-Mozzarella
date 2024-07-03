/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.codoacodopizzamozzarella;

import Entidades.Producto;
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
        Producto p = new Producto("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/fb3e9473-cd0d-43e0-ac42-519593a0f19d/d3f74ac-14415b13-a801-40c6-bdc6-7158dcf22e7e.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2ZiM2U5NDczLWNkMGQtNDNlMC1hYzQyLTUxOTU5M2EwZjE5ZFwvZDNmNzRhYy0xNDQxNWIxMy1hODAxLTQwYzYtYmRjNi03MTU4ZGNmMjJlN2UucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.N9fqaBnKwqrkmVbYll0nZFxiBjMtGZz7c1kY9MRIzjA", "Frijolito", "Frijol", "Luchador", 0);
        ProductoDAO dao = new ProductoDAO();
//        dao.persistirEntidad(p);
//        System.out.println("Se agrego correctamente");
System.out.println(dao.listaProductos());
    }
}
