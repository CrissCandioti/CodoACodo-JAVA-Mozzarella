/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Entidades.Producto;
import java.util.List;

/**
 *
 * @author criss
 */
public final class ProductoDAO extends DAO<Producto> {

    @Override
    public void persistirEntidad(Producto object) {
        super.persistirEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void borrarEntidad(Producto object) {
        super.borrarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void actualizarEntidad(Producto object) {
        super.actualizarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Producto BuscarProductoId(int id) {
        try {
            conectar();
            return em.find(Producto.class, id);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Producto> listaProductos() {
        try {
            conectar();
            return em.createQuery("Select p From Producto p", Producto.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            desconectar();
        }
        return null;
    }

}
