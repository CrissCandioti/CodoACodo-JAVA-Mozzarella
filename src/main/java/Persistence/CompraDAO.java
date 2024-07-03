/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Entidades.Compra;
import java.util.List;

/**
 *
 * @author criss
 */
public final class CompraDAO extends DAO<Compra> {

    @Override
    public void persistirEntidad(Compra object) {
        super.persistirEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void borrarEntidad(Compra object) {
        super.borrarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void actualizarEntidad(Compra object) {
        super.actualizarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Compra buscarCompraId(int id) {
        try {
            conectar();
            return em.find(Compra.class, id);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarCompraID de la clase CompraDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Compra> listaCompras() {
        try {
            conectar();
            return em.createQuery("Select c From Compra c", Compra.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error en el metodo listaCompras en la clase CompraDAO");
        } finally {
            desconectar();
        }
        return null;
    }

}
