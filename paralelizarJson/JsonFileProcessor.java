package paralelizarJson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

class JsonFileProcessor implements Runnable {

	JsonArray jsonArray;
	JsonReader jsonReader;

	// Objeto compartido
	JsonObjectMultiQueue theQueue;

	JsonFileProcessor(String fileName, JsonObjectMultiQueue theQueue) {
		this.theQueue = theQueue;

		try {
			// theObjectQueue = anObjectQueue;

			Path path = Paths.get(fileName);

			// Crear un InputStream para leer el archivo
			InputStream inputStream = Files.newInputStream(path);

			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

			// Crear un JsonReader para leer el contenido JSON
			jsonReader = Json.createReader(bufferedInputStream);

			// Leer el contenido del archivo JSON como un objeto JsonObject
			jsonArray = jsonReader.readArray();

			// Cerrar el JsonReader
			jsonReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║   Entrando Thread 1 (LECTURA )               	   ║");
		System.out.println("╚══════════════════════════════════════════════════╝");

		for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {

			theQueue.addObject(jsonObject);
			/*
			 * System.out.println("Object: "+jsonObject.toString());
			 * System.out.println("Array Size: "+ theObjectQueue.getSize());
			 */
		}

		theQueue.setLast();

	}

}
