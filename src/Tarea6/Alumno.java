/**
 * Clase que representa un Alumno con sus datos personales.
 * 
 * @author Julia
 */
package Tarea6;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Alumno implements java.io.Serializable{
	
		private int nia;
		private String nombre;
		private String apellidos;
		private char Genero;
		private Date fechaNacimiento;
		private String ciclo;
		private String curso;
		private String grupo;
		private static final long serialVersionUID = 1L;
		
		
		public Alumno(int nia, String nombre, String apellidos, char genero, Date fechaNacimiento, String ciclo,
				String curso, String grupo) {
			
			this.nia = nia;
			this.nombre = nombre;
			this.apellidos = apellidos;
			Genero = genero;
			this.fechaNacimiento = fechaNacimiento;
			this.ciclo = ciclo;
			this.curso = curso;
			this.grupo = grupo;  
		}


		public int getNia() {
			return nia;
		}


		public void setNia(int nia) {
			this.nia = nia;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getApellidos() {
			return apellidos;
		}


		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}


		public char getGenero() {
			return Genero;
		}


		public void setGenero(char genero) {
			Genero = genero;
		}


		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}


		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}


		public String getCiclo() {
			return ciclo;
		}


		public void setCiclo(String ciclo) {
			this.ciclo = ciclo;
		}


		public String getCurso() {
			return curso;
		}


		public void setCurso(String curso) {
			this.curso = curso;
		}


		public String getGrupo() {
			return grupo;
		}


		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}


		@Override
		public String toString() {
			return "Alumno [NIA=" + nia + ", nombre=" + nombre + ", Apellidos=" + apellidos + ", Genero=" + Genero
					+ ", fecha Nacimiento=" + fechaNacimiento + ", Ciclo=" + ciclo + ", Curso=" + curso + ", Grupo="
					+ grupo + "]";
		}
		
		@Override
		public boolean equals(Object o) {
		    if (this == o) return true;
		    if (o == null || getClass() != o.getClass()) return false;
		    Alumno alumno = (Alumno) o;
		    return nia == alumno.nia;
		}

		@Override
		public int hashCode() {
		    return Objects.hash(nia);
		}
		
}
