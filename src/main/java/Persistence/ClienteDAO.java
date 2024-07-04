/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Entidades.Cliente;
import java.util.List;

/**
 *
 * @author criss
 */
public final class ClienteDAO extends DAO<Cliente> {

    @Override
    public void persistirEntidad(Cliente object) {
        super.persistirEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void borrarEntidad(Cliente object) {
        super.borrarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void actualizarEntidad(Cliente object) {
        super.actualizarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Cliente buscarClienteId(int id) {
        try {
            conectar();
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarClienteId de la clase ClienteDAO");
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Cliente> listaClientes() {
        try {
            conectar();
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al traer la lista de clientes de la base de datos en la clase ClienteDAO");
        } finally {
            desconectar();
        }
        return null;
    }

    public Cliente buscarCLientePorEmail(String correoElectronico) {
        try {
            conectar();
            return (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.correoElectronico = :correoElectronico").setParameter("correoElectronico", correoElectronico).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarClientePorEmail en la clase ClienteDAO");
        } finally {
            desconectar();
        }
        return null;
    }

    public Cliente buscarClientePorEmailYCorreoLogin(String correoElectronico, String contrasena) {
        try {
            conectar();
            return (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.correoElectronico =:correoElectronico AND c.contrasena =:contrasena").setParameter("correoElectronico", correoElectronico).setParameter("contrasena", contrasena).getSingleResult();

        } catch (Exception e) {
            System.out.println("Error en el metodo buscarClientePorEmailYCorreoLogin de la clase CLIENTEDAO");
        } finally {
            desconectar();
        }
        return null;
    }

}
