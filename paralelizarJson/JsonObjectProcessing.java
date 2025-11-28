package paralelizarJson;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class JsonObjectProcessing implements Runnable {

	// Recurso compartido

	JsonObjectMultiQueue cola1;
	JsonObject jsonObject;
	int objectProcessed = 0;
	 JsonObjectMultiQueue cola2;

	public JsonObjectProcessing (JsonObjectMultiQueue cola1, JsonObjectMultiQueue cola2 ) {

		this.cola1 = cola1;
		this.cola2 = cola2;

	}

	@Override
	public void run() {

		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║  Entrando Thread 2 (NORMALIZACIÓN)               ║");
		System.out.println("╚══════════════════════════════════════════════════╝");

		while (!cola1.isLast()) {

			jsonObject = this.cola1.getObject();
			objectProcessed++;

			String title = jsonObject.getString("title");
			int year = jsonObject.getInt("year");
			JsonArray cast = jsonObject.getJsonArray("cast");
			JsonArray genres = jsonObject.getJsonArray("genres");

			System.out.println("Title: " + title);
			System.out.println("Year: " + year);
			System.out.println("Cast: " + cast.toString());

			System.out.println("Genres: " + genres.toString());

			if (jsonObject.containsKey("href") && !jsonObject.isNull("href")) {
				String href = jsonObject.isNull("href") ? null : jsonObject.getString("href", null);
				System.out.println("Href: " + href);
			}

			if (jsonObject.containsKey("extract") && !jsonObject.isNull("extract")) {
				String extract = jsonObject.isNull("extract") ? null : jsonObject.getString("extract", null);
				System.out.println("Extract: " + extract);
			}

			if (jsonObject.containsKey("thumbnail") && !jsonObject.isNull("thumbnail")) {
				String thumbnail = jsonObject.isNull("thumbnail") ? null : jsonObject.getString("thumbnail", null);
				System.out.println("thumbnail: " + thumbnail);

			}

			if (jsonObject.containsKey("thumbnail_width") && !jsonObject.isNull("thumbnail_width")) {
				int thumbnailWidth = jsonObject.isNull("thumbnail_width") ? null : jsonObject.getInt("thumbnail_width");
				System.out.println("Ancho: " + thumbnailWidth);
			}

			if (jsonObject.containsKey("thumbnail_height") && !jsonObject.isNull("thumbnail_height")) {
				int thumbnailHeight = jsonObject.isNull("thumbnail_height") ? null
						: jsonObject.getInt("thumbnail_height");
				System.out.println("Altura: " + thumbnailHeight);
			}

			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");

			System.out.println("Nº de objetos procesados: " + objectProcessed);
			cola2.addObject(jsonObject);
		}
		cola2.setLast();

	}
}
