package daoAccesoPeliculas;

import javax.json.JsonObject;

import daoAccesoPeliculas.Pelicula;

public interface InterfazDao<T> {

	void start();

	void stop();

	void reset();

	void normalizarJson();

	JsonObject buscar(int idBuscado);

	void insertar(Pelicula pelicula);

	void modificar(int id, Pelicula peliculaModificada);

	void borrar(int idABorrar);

	T getResult();

}
