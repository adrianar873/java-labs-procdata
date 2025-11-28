package daoAccesoPeliculas;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class AccesoPelículas {

	// -----------------------------
	// ATRIBUTOS COMPARTIDOS
	// -----------------------------
	private String rutaOriginal;
	private String rutaCopia;

	private InputStream inputStream;
	private OutputStream outputStream;

	private BufferedInputStream bufferedInputStream;
	private BufferedOutputStream bufferedOutputStream;

	private JsonReader jsonReader;
	private JsonWriter jsonWriter;

	// -----------------------------
	// CONSTRUCTOR
	// -----------------------------
	public AccesoPelículas(String rutaOriginal, String rutaCopia) {
		this.rutaOriginal = rutaOriginal;
		this.rutaCopia = rutaCopia;

		try {
			inputStream = Files.newInputStream(Paths.get(rutaCopia));
			bufferedInputStream = new BufferedInputStream(inputStream);
			jsonReader = Json.createReader(bufferedInputStream);
		} catch (IOException e) {
			System.err.println("Error inicializando AccesoPelículas");
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------
	// MÉTODO NORMALIZAR JSON
	// --------------------------------------------------------------------------------
	public void normalizarJson() {

		try (InputStream inputStream = Files.newInputStream(Paths.get(rutaOriginal));
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				OutputStream outputStream = new FileOutputStream(rutaCopia);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
				JsonReader jsonReader = Json.createReader(bufferedInputStream);
				JsonWriter jsonWriter = Json.createWriter(bufferedOutputStream)) {

			JsonArray jsonArray = jsonReader.readArray();
			JsonArrayBuilder constructorArrayJson = Json.createArrayBuilder();
			int idCont = 0;

			for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {

				String title = jsonObject.getString("title");
				int year = jsonObject.getInt("year");
				JsonArray cast = jsonObject.getJsonArray("cast");
				JsonArray genres = jsonObject.getJsonArray("genres");

				JsonObjectBuilder constructorOjetoJson = Json.createObjectBuilder().add("id", idCont)
						.add("title", title).add("year", year).add("cast", cast).add("genres", genres);

				// Normalizar campos
				constructorOjetoJson.add("href",
						jsonObject.containsKey("href")
								? (jsonObject.isNull("href") ? "NULL" : jsonObject.getString("href"))
								: "N/A");

				constructorOjetoJson.add("extract",
						jsonObject.containsKey("extract")
								? (jsonObject.isNull("extract") ? "NULL" : jsonObject.getString("extract"))
								: "N/A");

				constructorOjetoJson.add("thumbnail",
						jsonObject.containsKey("thumbnail")
								? (jsonObject.isNull("thumbnail") ? "NULL" : jsonObject.getString("thumbnail"))
								: "default");

				constructorOjetoJson.add("thumbnail_width",
						jsonObject.containsKey("thumbnail_width") && !jsonObject.isNull("thumbnail_width")
								? jsonObject.getInt("thumbnail_width")
								: 0);

				constructorOjetoJson.add("thumbnail_height",
						jsonObject.containsKey("thumbnail_height") && !jsonObject.isNull("thumbnail_height")
								? jsonObject.getInt("thumbnail_height")
								: 0);

				constructorOjetoJson.add("copyright", "Rodolfo Ucha Piñeiro");

				constructorArrayJson.add(constructorOjetoJson.build());
				idCont++;
			}

			jsonWriter.writeArray(constructorArrayJson.build());
			System.out.println("Normalización completada.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------
	// MÉTODO BUSCAR POR ID DE PELÍCULA
	// --------------------------------------------------------------------------------
	public JsonObject buscarPorId(int idBuscado) {
		try {
			JsonArray jsonArray = jsonReader.readArray();
			for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
				if (jsonObject.containsKey("id") && jsonObject.getInt("id") == idBuscado) {
					return jsonObject;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// --------------------------------------------------------------------------------
	// MÉTODO INSERTAR PELÍCULA
	// --------------------------------------------------------------------------------
	public void insertarPelicula(Pelicula pelicula) {
		try {
			// Cargar array desde JSON
			JsonArray jsonArray = jsonReader.readArray();
			JsonArrayBuilder constructorArrayJson = Json.createArrayBuilder();

			int idNuevo = 0;
			for (JsonObject obj : jsonArray.getValuesAs(JsonObject.class)) {
				constructorArrayJson.add(obj);
				idNuevo = obj.getInt("id") + 1;
			}

			JsonArrayBuilder castBuilder = Json.createArrayBuilder();
			for (String actor : pelicula.getCast())
				castBuilder.add(actor);

			JsonArrayBuilder generosBuilder = Json.createArrayBuilder();
			for (String genero : pelicula.getGeneros())
				generosBuilder.add(genero);

			JsonObjectBuilder nuevoObjeto = Json.createObjectBuilder().add("id", idNuevo)
					.add("title", pelicula.getTitulo()).add("year", pelicula.getAño()).add("cast", castBuilder.build())
					.add("genres", generosBuilder.build())
					.add("href", pelicula.getHref() != null ? pelicula.getHref() : "N/A")
					.add("extract", pelicula.getExtracto() != null ? pelicula.getExtracto() : "N/A")
					.add("thumbnail", pelicula.getPortada() != null ? pelicula.getPortada() : "default")
					.add("thumbnail_width", pelicula.getAnchoPortada())
					.add("thumbnail_height", pelicula.getAlturaPortada()).add("copyright", "Rodolfo Ucha Piñeiro");

			constructorArrayJson.add(nuevoObjeto.build());

			outputStream = new FileOutputStream(rutaCopia);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			jsonWriter = Json.createWriter(bufferedOutputStream);
			jsonWriter.writeArray(constructorArrayJson.build());
			jsonWriter.close();

			System.out.println("Película insertada con ID " + idNuevo);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------
	// MÉTODO MODIFICAR PELÍCULA
	// --------------------------------------------------------------------------------
	public void modificarPelicula(int id, Pelicula peliculaModificada) {
		try {
			JsonArray jsonArray = jsonReader.readArray();
			JsonArrayBuilder constructorArrayJson = Json.createArrayBuilder();

			for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
				if (jsonObject.getInt("id") == id) {

					JsonArrayBuilder castBuilder = Json.createArrayBuilder();
					for (String actor : peliculaModificada.getCast())
						castBuilder.add(actor);

					JsonArrayBuilder generosBuilder = Json.createArrayBuilder();
					for (String genero : peliculaModificada.getGeneros())
						generosBuilder.add(genero);

					JsonObjectBuilder nuevo = Json.createObjectBuilder().add("id", id)
							.add("title", peliculaModificada.getTitulo()).add("year", peliculaModificada.getAño())
							.add("cast", castBuilder.build()).add("genres", generosBuilder.build())
							.add("href",
									peliculaModificada.getHref() != null ? peliculaModificada.getHref()
											: jsonObject.getString("href"))
							.add("extract",
									peliculaModificada.getExtracto() != null ? peliculaModificada.getExtracto()
											: jsonObject.getString("extract"))
							.add("thumbnail",
									peliculaModificada.getPortada() != null ? peliculaModificada.getPortada()
											: jsonObject.getString("thumbnail"))
							.add("thumbnail_width", peliculaModificada.getAnchoPortada())
							.add("thumbnail_height", peliculaModificada.getAlturaPortada())
							.add("copyright", "Rodolfo Ucha Piñeiro");

					constructorArrayJson.add(nuevo.build());
				} else {
					constructorArrayJson.add(jsonObject);
				}
			}

			outputStream = new FileOutputStream(rutaCopia);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			jsonWriter = Json.createWriter(bufferedOutputStream);
			jsonWriter.writeArray(constructorArrayJson.build());
			jsonWriter.close();

			System.out.println("Película con ID " + id + " modificada correctamente.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------
	// MÉTODO BORRAR PELÍCULA
	// --------------------------------------------------------------------------------
	public void borrarPelicula(int idABorrar) {
		try {
			JsonArray jsonArray = jsonReader.readArray();
			JsonArrayBuilder constructorArrayJson = Json.createArrayBuilder();

			for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
				if (jsonObject.getInt("id") != idABorrar) {
					constructorArrayJson.add(jsonObject);
				}
			}

			outputStream = new FileOutputStream(rutaCopia);
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			jsonWriter = Json.createWriter(bufferedOutputStream);
			jsonWriter.writeArray(constructorArrayJson.build());
			jsonWriter.close();

			System.out.println("Película con ID " + idABorrar + " borrada correctamente.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
