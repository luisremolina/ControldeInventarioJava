/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista_Inventario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class Lista_Inventario {
    
     NodoLista_Inventario Primero, ultimo, aux, nuevo, ant, post, aux2;

    public Lista_Inventario() {
        Primero = ultimo = aux = nuevo = ant = post = null;
    }

    public void insertarcola(int id_producto, int cantidad, int precio, String Nombre) {
        if (Primero == null) {
            Primero = new NodoLista_Inventario(id_producto, cantidad, precio, Nombre);
            ultimo = Primero;
        } else {
            nuevo = new NodoLista_Inventario(id_producto, cantidad, precio, Nombre);
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }

    }
     public void guardar_datos() {
        try {
            FileWriter escribir_archivo = new FileWriter("Inventario.txt");
            BufferedWriter bw = new BufferedWriter(escribir_archivo);
            PrintWriter pw = new PrintWriter(bw);
            Character limite = ';';
            aux = Primero;
            while (aux != null) {
                pw.println(String.valueOf(aux.id_producto) + limite + aux.cantidad + limite + aux.precio + limite + aux.Nombre + limite);
                aux = aux.sig;
            }
            pw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha sucedido un error intentelo nuevamente");
        }
    }

    public void cargar_datos() {
        try {
            String[] Datos_lista = null;
            FileReader leer_archivo = new FileReader("Inventario.txt");
            BufferedReader br = new BufferedReader(leer_archivo);
            Character separador = ';';
            String datos = "";
            File archivo = new File("Inventario.txt");

            if (archivo.exists() && archivo.length() == 0) {
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            } else {
                while ((datos = br.readLine()) != null) {

                    Datos_lista = datos.split(String.valueOf(separador));

                    int id_producto = Integer.parseInt(Datos_lista[0]);
                    int cantidad = Integer.parseInt(Datos_lista[1]);
                    int precio = Integer.parseInt(Datos_lista[2]);
                    String Nombre = Datos_lista[3];
                  

                    insertarcola(id_producto, cantidad, precio, Nombre);
                }
                br.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No hay Inventario registrados");
        }
    }

    public String[] buscar(int busc) {

        String datos[] = new String[3];
        aux = Primero;
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "No se encuentra este articulo en el inventario");
        } else {
            while (aux != null) {
                if (aux.id_producto == busc) {
                    datos[0] = aux.Nombre;
                    datos[1] = String.valueOf(aux.cantidad);
                    datos[2] = String.valueOf(aux.precio);
                    break;
                }
                aux = aux.sig;
            }
        }
        return datos;
    }

    public void despliegaLista() {
        String cad = "";
        aux = Primero;
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "La persona no cuenta con antecedentes penales");
        } else {

            while (aux != null) {

                cad = cad + "El inventario con ID: " 
                        + aux.id_producto + " \n Tiene un precio de: " 
                        + aux.precio + " \n y hay una cantidad de: " + aux.cantidad ;
                       
                aux = aux.sig;

            }
            JOptionPane.showMessageDialog(null, cad);

        }
    }
    
    
}
