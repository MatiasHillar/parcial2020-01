package isi.died2020.parcial01.ejercicio02.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import isi.died2020.parcial01.ejercicio02.db.BaseDeDatos;
import isi.died2020.parcial01.ejercicio02.db.BaseDeDatosExcepcion;
import isi.died2020.parcial01.ejercicio02.db.ExcepcionNoEsProfesor;
import isi.died2020.parcial01.ejercicio02.dominio.*;
import isi.died2020.parcial01.ejercicio02.dominio.Docente.Cargo;
import utn.frsf.isi.died2020.tp07.modelo.Material;


public class MySysAcadImpl implements MySysAcad, Comparator<Examen> {
	private static final BaseDeDatos DB = new BaseDeDatos();


	private List<Materia> materia = new ArrayList<Materia>();
	
	@Override
	public void registrarMateria(Materia d) {
		this.materia.add(d);
	}
	
	private List<Docente> docentes = new ArrayList<Docente>();
	
	@Override
	public void registrarDocente(Docente d) {
		this.docentes.add(d);
	}
	
	private List<Alumno> alumnos = new ArrayList<Alumno>();
	
	@Override
	public void registrarAlumnos(Alumno d) {
		this.alumnos.add(d);
	}
	
	public void registrarNota(Integer nota, Examen e) {
		e.setNota(nota);
		if(nota >= 6) {
			Alumno a = e.getAlumno();
			a.promocionar(e.getMateria());
		}
		
	}

	@Override
	public void inscribirAlumnoCursada(Docente d, Alumno a, Materia m, Integer cicloLectivo) {
		Inscripcion insc = new Inscripcion(cicloLectivo,Inscripcion.Estado.CURSANDO);
		d.agregarInscripcion(insc);
		a.addCursada(insc);
		m.addInscripcion(insc);
		
		//	DB.guardar(insc);
	}

	private Predicate<Docente> puedeAsignarExamen = 
			d -> (d.getCargo() == Cargo.PROFESOR);
	
	@Override
	public void inscribirAlumnoExamen(Docente d, Alumno a, Materia m) throws ExcepcionNoEsProfesor, ExcepcionBD {
		Examen e = new Examen();
		a.addExamen(e);
		if(puedeAsignarExamen.test(d)) {
		d.agregarExamen(e);
		m.addExamen(e);
		try {
			DB.guardar(e);
		} catch (BaseDeDatosExcepcion e1) {
			throw new ExcepcionBD();
		}
		}
		else throw new ExcepcionNoEsProfesor();
	}
	@Override
	public void registrarNota(Examen e, Integer nota) {
		e.setNota(nota);
		if(nota >= 6) {
			e.getAlumno().promocionar(e.getMateria());
		}
	}

	@Override
	public List<Examen> topNExamenes(Materia m, Integer n) {
		return m.getExamenes().stream()
				.sorted((e1,e2) -> this.compare(e1,e2))
				.limit(n)
				.collect(Collectors.toList());
			
	}

	@Override
	public int compare(Examen arg0, Examen arg1) {
	Integer ret = arg0.getNota() - arg1.getNota();
	if(ret == 0) {
		return arg0.getFecha().compareTo(arg1.getFecha());
	}
	else return ret;
	}


}
