package aemet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Conexión a la API de AEMET. Realiza la primera petición para obtener la URL
 * de "datos" y luego hace la segunda petición para obtener el array de JSON
 * final.
 */
public class AemetConnection {

	private final String apiKey;

	public AemetConnection(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * Obtiene el array de estaciones de la API AEMET. Hace dos peticiones: primero
	 * obtiene la URL de "datos", luego devuelve el JSON final como JsonArray.
	 *
	 * @return JsonArray con todas las estaciones, o null si hay error.
	 */
	public JsonArray getEstacionesArray() {
		try {
			String urlString = "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key="
					+ apiKey;
			JsonObject firstJson = getJsonObjectFromUrl(urlString);

			String datosUrl = firstJson.getString("datos");

			return getJsonArrayFromUrl(datosUrl);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private JsonObject getJsonObjectFromUrl(String urlString) throws Exception {
		@SuppressWarnings("deprecation")
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		try (InputStream is = conn.getInputStream();
				JsonReader reader = Json.createReader(new InputStreamReader(is, "UTF-8"))) {
			return reader.readObject();
		} finally {
			conn.disconnect();
		}
	}

	private JsonArray getJsonArrayFromUrl(String urlString) throws Exception {
		@SuppressWarnings("deprecation")
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		try (InputStream is = conn.getInputStream();
				JsonReader reader = Json.createReader(new InputStreamReader(is, "UTF-8"))) {
			return reader.readArray();
		} finally {
			conn.disconnect();
		}

	}
}
