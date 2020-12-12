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
public class ListaDoble_Inventario {

    NodoDobleLista_Inventario inicio, fin;

    public ListaDoble_Inventario() {
        inicio = fin = null;
    }
    // METODO PARA SABER SI HAY DATOS EN LA LISTA

    public boolean esVacia() {
        return inicio == null;
    }

    // METODO PARA AGREGAR AL FINAL
    public void addtoEnd(int id_producto, int cantidad, int precio, String Nombre) {
        if (!esVacia()) {
            fin = new NodoDobleLista_Inventario(id_producto, cantidad, precio, Nombre, null, fin);
            fin.ant.sig = fin;
        } else {
            inicio = fin = new NodoDobleLista_Inventario(id_producto, cantidad, precio, Nombre);
        }
    }

    public void addtoStart(int id_producto, int cantidad, int precio, String Nombre) {
        if (!esVacia()) {
            inicio = new NodoDobleLista_Inventario(id_producto, cantidad, precio, Nombre, inicio, null);
            inicio.sig.ant = inicio;
        } else {
            inicio = fin = new NodoDobleLista_Inventario(id_producto, cantidad, precio, Nombre);
        }
    }
    //METODO PARA MOSTRAR LA LISTA DE INICIO A FIN 

    public void showStartEnd() {
        if (!esVacia()) {
            String value = "<->";
            NodoDobleLista_Inventario aux = inicio;
            while (aux != null) {

                value = value
                        + "["
                        + aux.id_producto + ","
                        + aux.Nombre + ","
                        + aux.precio + ","
                        + aux.cantidad + ","
                        + "]<->";
                aux = aux.sig;
            }
            JOptionPane.showMessageDialog(null, value, "Mostrando lista de inicio a fin",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //METODO PARA MOSTRAR LA LISTA DE FIN A INICIO 

    public void showEndStart() {
        if (!esVacia()) {
            String value = "<->";
            NodoDobleLista_Inventario aux = fin;
            while (aux != null) {

                value = value
                        + "["
                        + aux.id_producto + ","
                        + aux.Nombre + ","
                        + aux.precio + ","
                        + aux.cantidad + ","
                        + "]<->";
                aux = aux.ant;
            }
            JOptionPane.showMessageDialog(null, value, "Mostrando lista de fin a inicio",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // METODO PARA MOSTRAR LOS DATOS ALMACENADOS EN LOS ARCHIVOS PLANOS

    public String despliegaLista() {
        String cad = "";
        NodoDobleLista_Inventario aux = inicio;
        if (aux == null) {
            cad = "NO HAY PRODUCTOS EN EL ALMACEN";
        } else {

            while (aux != null) {

                cad = cad + "ID: " + aux.id_producto + ","
                        + "Nombre: " + aux.Nombre + ","
                        + "Precio: " + aux.precio + ","
                        + "Cantidad: " + aux.cantidad + "\n";

                aux = aux.sig;

            }
//            JOptionPane.showMessageDialog(null, cad);
        }
        return cad;
    }
    // METODO PARA BUSCAR UN PRODUCTO POR SU ID 

    public String[] buscar(int busc) {

        String datos[] = new String[3];
        NodoDobleLista_Inventario aux = inicio;
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "No se encuentra informacion con esta id");
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
    // METODO PARA ELIMINAR UN NODO POR SU ID 

//    public void delete(int busc) {
//
//        NodoDobleLista_Inventario aux = inicio;
//        boolean encontrado = false;
//        while (aux != null && !encontrado) {
//            encontrado = (aux.id_producto == busc);
//            if (!encontrado) {
//                aux = aux.sig;
//            }
//        }
//
////        System.out.println("EL VALOR DE AUXILIAR ES :" + aux.id_producto + " Y Encontrado:"+encontrado);
//        if (encontrado) {
//            if (aux == inicio) 
//                inicio = aux.sig;
////                System.out.println("entro a la primera condision");
//             else {
//                aux.ant.sig = aux.sig;
//                System.out.println("entro a la segunda condision");
//                if (aux.sig != null) 
//                    aux.sig.ant = aux.ant;
//                    System.out.println("entro a la tercera condision");
//                
//            }
//            aux = null;
//        }
//
//    }
    public void delete(int x) {
        NodoDobleLista_Inventario actual = new NodoDobleLista_Inventario();
        NodoDobleLista_Inventario atras = new NodoDobleLista_Inventario();
        actual = inicio;
        atras = null;

        while (actual != null) {
            if (actual.id_producto == x) {
                if (actual == inicio) {
                    inicio = inicio.sig;
                    inicio.ant = null;
                }else if(actual == fin){
                    atras = actual.ant;
                    atras.sig = null;
                    fin = atras;
                    
                } else {
                    atras.sig = actual.sig;
                    actual.sig.ant = actual.ant;
                }
            }
            atras = actual;
            actual = actual.sig;
        }
        guardar_datos();
        JOptionPane.showMessageDialog(null, despliegaLista());
    }
    
    public void modificar(int busc, String name, int precio, int cantidad) {

        NodoDobleLista_Inventario aux = inicio;
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "No hay rutas registrados");
        }
        while (aux != null) {
            if (aux.id_producto == busc) {
                aux.Nombre = name;
                aux.precio = precio;
                aux.cantidad = cantidad;
              
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro con exito");
                guardar_datos();
                break;
            }
            aux = aux.sig;
        }
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "La ruta no existe");
        }
    }

    // METODOS PARA CREAR LOS ARCHIVOS PLANOS
    public void guardar_datos() {
        try {
            FileWriter escribir_archivo = new FileWriter("Inventario.txt");
            BufferedWriter bw = new BufferedWriter(escribir_archivo);
            PrintWriter pw = new PrintWriter(bw);
            Character limite = ';';
            NodoDobleLista_Inventario aux = inicio;
            while (aux != null) {
                pw.println(String.valueOf(aux.id_producto) + limite + aux.cantidad
                        + limite + aux.precio + limite + aux.Nombre + limite);
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

                    addtoStart(id_producto, cantidad, precio, Nombre);
                }
                br.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No hay Inventario registrados");
        }
    }

}
