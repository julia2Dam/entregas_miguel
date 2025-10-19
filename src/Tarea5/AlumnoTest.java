package Tarea5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;

class AlumnoTest {

    private final String rutaPrueba = "testAlumnos.dat";

    @BeforeEach
    void setUp() {
        // Borrar el fichero antes de cada prueba para empezar limpio
        File f = new File(rutaPrueba);
        if (f.exists()) f.delete();
    }

    @AfterEach
    void tearDown() {
        // Borrar el fichero después de cada prueba
        File f = new File(rutaPrueba);
        if (f.exists()) f.delete();
    }

    @Test
    void testEscrituraLecturaAlumno() {
        // Crear un alumno de prueba
        Alumno a1 = new Alumno(12345, "Juan", "Pérez", 'M', new Date(), "DAM", "1", "A");

        // Escribir el alumno en el fichero
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(rutaPrueba)))) {
            oos.writeObject(a1);
        } catch (IOException e) {
            fail("Error al escribir el fichero: " + e.getMessage());
        }

        // Leer el alumno del fichero
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(rutaPrueba)))) {
        	
            Alumno leido = (Alumno) ois.readObject();

            // Comprobar que todos los campos coinciden
            assertEquals(a1.getNia(), leido.getNia());
            assertEquals(a1.getNombre(), leido.getNombre());
            assertEquals(a1.getApellidos(), leido.getApellidos());
            assertEquals(a1.getGenero(), leido.getGenero());
            assertEquals(a1.getCiclo(), leido.getCiclo());
            assertEquals(a1.getCurso(), leido.getCurso());
            assertEquals(a1.getGrupo(), leido.getGrupo());

        } catch (IOException | ClassNotFoundException e) {
            fail("Error al leer el fichero: " + e.getMessage());
        }
    }
}
