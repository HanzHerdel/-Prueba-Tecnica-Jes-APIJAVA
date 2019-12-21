package net.qtech.modelo;

import java.sql.Date;

public class Fiscalia {
	private int Id;
	private String Departamento;
	private String Municipio;
	private String Aldea;
	private String Colonia;
	private String Referencia;
	private String Tel;
	private String Dir;
	private String Coordenadas;
	private Date FechaModificado;
	private String Nombre;	
	public Fiscalia(int id, String departamento, String municipio, String aldea, String colonia, String referencia,
			String tel, String dir, String coordenadas, Date fechaModificado,String nombre) {
		super();
		Id = id;
		Departamento = departamento;
		Municipio = municipio;
		Aldea = aldea;
		Colonia = colonia;
		Referencia = referencia;
		Tel = tel;
		Dir = dir;
		Coordenadas = coordenadas;
		FechaModificado = fechaModificado;
		Nombre=nombre;
	}
	
	public Fiscalia() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public String getMunicipio() {
		return Municipio;
	}

	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getAldea() {
		return Aldea;
	}

	public void setAldea(String aldea) {
		Aldea = aldea;
	}

	public String getColonia() {
		return Colonia;
	}

	public void setColonia(String colonia) {
		Colonia = colonia;
	}

	public String getReferencia() {
		return Referencia;
	}

	public void setReferencia(String referencia) {
		Referencia = referencia;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getDir() {
		return Dir;
	}

	public void setDir(String dir) {
		Dir = dir;
	}

	public String getCoordenadas() {
		return Coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		Coordenadas = coordenadas;
	}

	public Date getFechaModificado() {
		return FechaModificado;
	}

	public void setFechaModificado(Date fechaModificado) {
		FechaModificado = fechaModificado;
	}
	public String getNombre() {
		return Dir;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Override
	public String toString() {
		return "Fiscalia [Id=" + Id + ", Departamento=" + Departamento + ", Municipio=" + Municipio + ", Aldea=" + Aldea
				+ ", Colonia=" + Colonia + ", Referencia=" + Referencia + ", Tel=" + Tel + ", Dir=" + Dir
				+ ", Coordenadas=" + Coordenadas + ", FechaModificado=" + FechaModificado + ", Nombre=" + Nombre + "]";
	}

	
	
}
