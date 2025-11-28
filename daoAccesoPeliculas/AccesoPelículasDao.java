package daoAccesoPeliculas;

import javax.json.JsonObject;

import daoAccesoPeliculas.Pelicula;

public class AccesoPelículasDao implements InterfazDao<AccesoPelículas> {

	private AccesoPelículas access;

	@Override
	public void start() {
		access = new AccesoPelículas("movies.json", "moviesCopy.json");

	}

	@Override
	public void stop() {
		access = null;

	}

	@Override
	public void reset() {
		access = null;
		access = new AccesoPelículas("movies.json", "movies_normalized.json");

	}

	@Override
	public void normalizarJson() {
		access.normalizarJson();

	}

	@Override
	public void insertar(Pelicula pelicula) {
		access.insertarPelicula(pelicula);
	}

	@Override
	public void modificar(int id, Pelicula peliculaModificada) {
		access.modificarPelicula(id, peliculaModificada);
	}

	@Override
	public JsonObject buscar(int idBuscado) {
		return access.buscarPorId(idBuscado);
	}

	@Override
	public void borrar(int idABorrar) {
		access.borrarPelicula(idABorrar);
	}

	@Override
	public AccesoPelículas getResult() {
		return access;
	}

}
