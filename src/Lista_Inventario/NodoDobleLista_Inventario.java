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
public class NodoDobleLista_Inventario {
    int id_producto, cantidad, precio;
    String Nombre;
    NodoDobleLista_Inventario sig,ant;
    
    public NodoDobleLista_Inventario(){
        
    }
    // CONSTRUCTOR PARA CUANDO YA EXISTEN NODOS
    
    public NodoDobleLista_Inventario(int id_producto, int cantidad, int precio, String Nombre, NodoDobleLista_Inventario sig, NodoDobleLista_Inventario ant) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.Nombre = Nombre;
        this.sig = sig;
        this.ant = ant;
    }
    
    // CONSTRUCTOR PARA CUANDO TODAVIA NO EXISTEN NODOS
    
    public NodoDobleLista_Inventario(int id_producto, int cantidad, int precio, String Nombre){
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.Nombre = Nombre;
        this.sig = null;
        this.ant = null;
    }
    
    
}
