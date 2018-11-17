package com.afaf.agendafrontal.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contacto {
	
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String nombre;
	
	@JsonProperty
	private String apellidos;
	
	@JsonProperty
	private Long telefono;
	
	@JsonProperty
	private String email;
	
	public Contacto() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
