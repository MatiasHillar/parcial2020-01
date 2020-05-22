package isi.died2020.parcial01.ejercicio02.app;

public class ExcepcionBD extends Exception {

	
	public ExcepcionBD() {
		super("Hubo un problema al guardar la inscripción del alumno al examen");
	}
	
}
