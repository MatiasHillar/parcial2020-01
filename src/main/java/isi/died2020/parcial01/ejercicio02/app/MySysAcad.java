package isi.died2020.parcial01.ejercicio02.app;

import java.util.List;

import isi.died2020.parcial01.ejercicio02.db.BaseDeDatosExcepcion;
import isi.died2020.parcial01.ejercicio02.db.ExcepcionNoEsProfesor;
import isi.died2020.parcial01.ejercicio02.dominio.*;



public interface MySysAcad {
	
	public void registrarMateria(Materia d);
	
	
	public void registrarDocente(Docente d) ;
	
	
	public void registrarAlumnos(Alumno d);
	/**
	 * crea una nueva instancia de Inscripcion y 
	 * asigna la inscripcion a la lista de inscripciones del alumno, 
	 * de la materia y del docente
	 */
	public void inscribirAlumnoCursada(Docente d,Alumno a, Materia m,Integer cicloLectivo);

	public void inscribirAlumnoExamen(Docente d,Alumno a, Materia m) throws ExcepcionNoEsProfesor, ExcepcionBD;
	
	public void registrarNota(Examen e, Integer nota);
	
	public List<Examen> topNExamenes(Materia m, Integer n);

}
