/**
 * Clase principal del ejercicio 1.1
 * Gestiona la creación de alumnos y su almacenamiento en fichero binario.
 *
 * @author Julia
 * @version 1.0
 */

package Tarea4;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ejercicio1DataOutput { 

    /**
     * Método principal que muestra el menú y gestiona la creación de alumnos.
     *
     * @param args argumentos de línea de comandos
     */
	
	// Programa que guarda los datos de 5 alumnos en un fichero binario campo a campo
	// Cada alumno se guarda inmediatamente después de introducirlo
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String ruta;

        do {
            System.out.println("Menú de Gestión de Alumnos");
            System.out.println("1. Crear los 5 alumnos y guardarlos en fichero");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt(); 
            sc.nextLine(); // Limpia el salto de línea pendiente después de nextInt()

            switch (opcion) {
                case 1:
                    System.out.print("Nombre y ruta del fichero (ej: alumnos.dat): ");
                    ruta = sc.nextLine();

                    try (DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(new FileOutputStream(ruta)))) {

                        for (int i = 0; i < 5; i++) {
                            System.out.println("\n--- Alumno " + (i + 1) + " ---");
                            Alumno a = leerAlumno(sc);   // Crea el alumno 
                            escribirAlumno(dos, a);      // Guarda alumno en el fichero campo a campo
                            dos.flush();                 // Limpia el tubo de salida
                        }

                        System.out.println("\nFichero creado y 5 alumnos guardados correctamente.");

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
        sc.nextLine(); // Limpia salto de línea pendiente

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Género (M/F): ");
        char genero = sc.nextLine().charAt(0);

        System.out.print("Fecha nacimiento (dd/MM/yyyy): ");
        Date fecha = null; // variable para guardar la fecha 
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); // formato de la fecha
        
        // Bucle que asegura que el usuario escriba la fecha en el formato correcto
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

    /**
     * Guarda un objeto Alumno en un DataOutputStream escribiendo cada campo.
     *
     * @param dos Flujo de salida donde se escriben los datos
     * @param a Alumno a guardar
     * @throws IOException Si hay un error de escritura en el fichero
     */
    public static void escribirAlumno(DataOutputStream dos, Alumno a) throws IOException {
        dos.writeInt(a.getNia());                         // NIA (int)
        dos.writeUTF(a.getNombre());                      // Nombre (String)
        dos.writeUTF(a.getApellidos());                   // Apellidos (String)
        dos.writeChar(a.getGenero());                     // Género (char)
        dos.writeLong(a.getFechaNacimiento().getTime());  // Fecha como long (milisegundos)
        dos.writeUTF(a.getCiclo());                       // Ciclo (String)
        dos.writeUTF(a.getCurso());                       // Curso (String)
        dos.writeUTF(a.getGrupo());                       // Grupo (String)
    }
}

