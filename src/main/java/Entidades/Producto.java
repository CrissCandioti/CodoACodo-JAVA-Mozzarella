/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author criss
 */
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 2048)
    private String imagen;
    private String nombre;
    private String ingredientes;
    private String descripcion;
    private double precio;

    public Producto() {
    }

    public Producto(String imagen, String nombre, String ingredientes, String descripcion, double precio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto(int id, String imagen, String nombre, String ingredientes, String descripcion, double precio) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", imagen=" + imagen + ", nombre=" + nombre + ", ingredientes=" + ingredientes + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }

}
