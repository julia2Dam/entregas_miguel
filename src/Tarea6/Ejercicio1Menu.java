/**
  * Programa de gestión de alumnos con fichero binario.
 *  Permite:
 * - Crear un fichero vacío
 * - Seleccionar un fichero existente
 * - Cargar alumnos
 * - Mostrar todos los alumnos
 * 
 * @author Julia
 * @version 1.0
 */
package Tarea6;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Ejercicio1Menu {

    //Ruta del fichero
    private static String ficheroActual = null;

    /**
     * Método principal que muestra el menú y gestiona las opciones
     * 
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--- Menú Gestión de Alumnos ---");
            System.out.println("1. Crear fichero vacío");
            System.out.println("2. Seleccionar fichero existente");
            System.out.println("3. Cargar alumno");
            System.out.println("4. Mostrar todos los alumnos");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre y ruta del nuevo fichero: ");
                    ficheroActual = sc.nextLine();
                    crearFichero(ficheroActual);
                    break;
                case 2:
                    System.out.print("Nombre y ruta del fichero existente: ");
                    ficheroActual = sc.nextLine();
                    if (!new File(ficheroActual).exists()) {
                        System.out.println("El fichero no existe.");
                        ficheroActual = null;
                    }
                    break;
                case 3:
                    if (ficheroActual != null) {
                        Alumno a = leerAlumno(sc);
                        guardarAlumno(a, ficheroActual);
                    } else {
                        System.out.println("Primero debes crear o seleccionar un fichero.");
                    }
                    break;
                case 4:
                    if (ficheroActual != null) {
                        mostrarAlumnos(ficheroActual);
                    } else {
                        System.out.println("Primero debes crear o seleccionar un fichero.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    /**
     * Crea un fichero vacío.
     * 
     * @param ruta ruta y nombre del fichero
     */
    public static void crearFichero(String ruta) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {            
            System.out.println("Fichero vacío creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de un alumno desde teclado y devuelve un objeto Alumno.
     * 
     * @param sc Scanner para leer los datos
     * @return Alumno creado con los datos introducidos
     */
    public static Alumno leerAlumno(Scanner sc) {
    	
        System.out.print("NIA: ");
        int nia = sc.nextInt(); sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Género (M/F): ");
        char genero = sc.nextLine().charAt(0);

        System.out.print("Fecha nacimiento (dd/MM/yyyy): ");
        Date fecha = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        while (fecha == null) {
            try {
                String s = sc.nextLine();
                fecha = formato.parse(s);
            } catch (ParseException e) {
                System.out.print("Formato inválido. Escribe dd/MM/yyyy: ");
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
     * Guarda un alumno en el fichero usando ObjectOutputStream.
     * Lee primero los alumnos existentes y luego reescribe todo junto con el nuevo.
     * 
     * @param a Alumno a guardar
     * @param ruta ruta del fichero
     */
    public static void guardarAlumno(Alumno a, String ruta) {
    	
        List<Alumno> lista = new ArrayList<>();

        // Leer alumnos existentes
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            while (true) {
                try {
                    lista.add((Alumno) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // si el fichero está vacío o no existe aún, no pasa nada
        }

        // Añadir nuevo alumno
        lista.add(a);

        // Escribir todo de nuevo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            for (Alumno al : lista) {
                oos.writeObject(al);
            }
            System.out.println("Alumno guardado.");
        } catch (IOException e) {
            System.out.println("Error al guardar el alumno: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los alumnos de un fichero.
     * 
     * @param ruta ruta del fichero
     */
    public static void mostrarAlumnos(String ruta) {
    	
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            while (true) {
                try {
                    Alumno a = (Alumno) ois.readObject();
                    System.out.println(a);
                    System.out.println("--------------------------");
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}

