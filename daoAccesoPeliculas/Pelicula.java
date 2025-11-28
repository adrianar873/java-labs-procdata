package daoAccesoPeliculas;

import java.util.ArrayList;

public class Pelicula {
	private String titulo;
	private int año;
	private ArrayList<String> cast;
	private ArrayList<String> generos;
	private String href;
	private String extracto;
	private String portada;
	private int anchoPortada;
	private int alturaPortada;

	public Pelicula(String titulo, int año, ArrayList<String> cast, ArrayList<String> generos, String href,
			String extracto, String portada, int anchoPortada, int alturaPortada) {
		this.titulo = titulo;
		this.año = año;
		this.cast = cast;
		this.generos = generos;
		this.href = href;
		this.extracto = extracto;
		this.portada = portada;
		this.anchoPortada = anchoPortada;
		this.alturaPortada = alturaPortada;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public ArrayList<String> getCast() {
		return cast;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public ArrayList<String> getGeneros() {
		return generos;
	}

	public void setGeneros(ArrayList<String> generos) {
		this.generos = generos;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getExtracto() {
		return extracto;
	}

	public void setExtracto(String extracto) {
		this.extracto = extracto;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public int getAnchoPortada() {
		return anchoPortada;
	}

	public void setAnchoPortada(int anchoPortada) {
		this.anchoPortada = anchoPortada;
	}

	public int getAlturaPortada() {
		return alturaPortada;
	}

	public void setAlturaPortada(int alturaPortada) {
		this.alturaPortada = alturaPortada;
	}

	@Override
	public String toString() {
		return titulo;
	}

}
