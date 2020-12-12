/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista_Inventario;

/**
 *
 * @author Luis
 */
public class NodoLista_Inventario {

    int id_producto, cantidad, precio;
    String Nombre;
    NodoLista_Inventario sig;
    
    
    public NodoLista_Inventario(int id_producto, int cantidad, int precio, String Nombre, NodoLista_Inventario sig) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.Nombre = Nombre;
        this.sig = sig;
    }
    
    

    public NodoLista_Inventario(int id_producto, int cantidad, int precio, String Nombre) {

        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.Nombre = Nombre;
        this.sig = null;
    }

    
}
