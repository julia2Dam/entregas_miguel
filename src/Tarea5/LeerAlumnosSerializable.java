/**
 * Programa que lee objetos Alumno desde un fichero binario (serializado)
 * y los muestra por pantalla.
 *
 * El nombre y ruta del fichero se piden al usuario por teclado.
 * Usa ObjectInputStream para leer los objetos serializados.
 */

package Tarea5;

import java.io.*;
import java.util.Scanner;

public class LeerAlumnosSerializable {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre o ruta del fichero (ej: alumnosObjetos.dat): ");
        String ruta = sc.nextLine();

        // Intentamos abrir el fichero y leer los objetos Alumno
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ruta)))) {
        	
        	System.out.println("");
            System.out.println("Leyendo alumnos del fichero...");
            System.out.println("");

            for (int i = 0; i < 5; i++) {
                try {
                    Alumno a = (Alumno) ois.readObject();
                    System.out.println(a);
                    System.out.println("----------------------------");
                } catch (ClassNotFoundException | IOException e) {
                    System.err.println("Error leyendo el objeto: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println(" Error al leer el fichero: " + e.getMessage());
        }

        sc.close();
    }
}

