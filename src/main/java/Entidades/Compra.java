/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author criss
 */
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "cliente_id") // nombre de la columna en la tabla Compra que hace referencia a Cliente
    private Cliente cliente;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Compra_Producto",
            joinColumns = @JoinColumn(name = "Compra_id"),
            inverseJoinColumns = @JoinColumn(name = "Producto_id")
    )
    private List<Producto> productos;
    private String direccion;

    public Compra() {
    }

    public Compra(Cliente cliente, List<Producto> productos, String direccion) {
        this.cliente = cliente;
        this.productos = productos;
        this.direccion = direccion;
    }

    public Compra(int id, Cliente cliente, List<Producto> productos, String direccion) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", cliente=" + cliente + ", productos=" + productos + ", direccion=" + direccion + '}';
    }

}
