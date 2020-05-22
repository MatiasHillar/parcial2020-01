package isi.died2020.parcial01.ejercicio02.db;

public class ExcepcionNoEsProfesor extends Exception {

	public ExcepcionNoEsProfesor() {
		super("El docente indicado no tiene el cargo PROFESOR");
	}
	
}
