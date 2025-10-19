/**
 * Clase principal del ejercicio 1.1
 * Gestiona la creación de alumnos y su almacenamiento en un fichero binario
 * mediante serialización de objetos.
 *
 * Cada alumno se guarda justo después de introducirlo por teclado.
 *
 * @author Julia
 * @version 1.0
 */

package Tarea5;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio1Serializable {

    /**
     * Método principal que muestra el menú y gestiona la creación de alumnos.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String ruta;

        do {
            System.out.println("Menú de Gestión de Alumnos (modo objetos)");
            System.out.println("1. Crear los 5 alumnos y guardarlos en fichero binario");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpia salto de línea 

            switch (opcion) {
                case 1:
                    System.out.print("Nombre y ruta del fichero (ej: alumnosObjetos.dat): ");
                    ruta = sc.nextLine();

                     try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)))) {

                        // Bucle para crear y guardar los 5 alumnos
                        for (int i = 0; i < 5; i++) {
                            System.out.println("\n--- Alumno " + (i + 1) + " ---");
                            Alumno a = leerAlumno(sc);  // crea el objeto Alumno con los datos
                            oos.writeObject(a);         // guarda el objeto completo
                            oos.flush();                // vacía el buffer 
                        }

                        System.out.println("\nFichero creado y 5 alumnos guardados correctamente (modo objetos).");

                    } catch (IOException e) {
                        System.err.println("Error al escribir el fichero: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }

    /**
     * Lee los datos de un alumno desde teclado y devuelve un objeto Alumno.
     *
     * @param sc Scanner para leer los datos
     * @return Alumno con los datos introducidos
     */
    public static Alumno leerAlumno(Scanner sc) {
        System.out.print("NIA: ");
        int nia = sc.nextInt();
        sc.nextLine(); // limpia salto de línea pendiente

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Género (M/F): ");
        char genero = sc.nextLine().charAt(0);

        System.out.print("Fecha nacimiento (dd/MM/yyyy): ");
        Date fecha = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        // Bucle hasta que se escriba una fecha válida
        while (fecha == null) {
            try {
                String s = sc.nextLine();
                fecha = formatoFecha.parse(s);
            } catch (ParseException e) {
                System.out.print("❌ Formato inválido. Escribe así: dd/MM/yyyy: ");
            }
        }

        System.out.print("Ciclo: ");
        String ciclo = sc.nextLine();

        System.out.print("Curso: ");
        String curso = sc.nextLine();

        System.out.print("Grupo: ");
        String grupo = sc.nextLine();

        return new Alumno(nia, nombre, apellidos, genero, fecha, ciclo, curso, grupo);
    }
}


