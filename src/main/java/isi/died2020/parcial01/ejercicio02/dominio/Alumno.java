package isi.died2020.parcial01.ejercicio02.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import isi.died2020.parcial01.ejercicio02.dominio.Inscripcion.Estado;

public class Alumno {
	
	private static Integer ID_GENERATOR=0;
	
	private Integer id;
	private String nombre;

	private List<Inscripcion> materiasCursadas;
	private List<Examen> examenes;
	
	public Alumno() {
		this.id = ID_GENERATOR++;
		this.materiasCursadas = new ArrayList<Inscripcion>();
		this.examenes = new ArrayList<Examen>();
	}
	
	
	public Alumno(String nombre) {
		this();
		this.nombre = nombre;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addExamen(Examen e) {
		this.examenes.add(e);
		e.setAlumno(this);
	}
	
	public void addCursada(Inscripcion e) {
		this.materiasCursadas.add(e);
		e.setInscripto(this);
	}
	
	public void promocionar(Materia m) {
		List<Inscripcion> l = this.materiasCursadas.stream()
		.filter(t -> t.getMateria() == m)
		.collect(Collectors.toList());
		int tam = l.size();
		l.get(tam).setEstado(Estado.PROMOCIONADO);
	}

}
