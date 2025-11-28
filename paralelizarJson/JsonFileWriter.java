package paralelizarJson;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class JsonFileWriter implements Runnable {

	JsonObjectMultiQueue theQueue;
	JsonObject jsonObject;
	int objectProcessed = 0;

	public JsonFileWriter(JsonObjectMultiQueue Queue) {
		this.theQueue = Queue;
	}

	@Override
	public void run() {
		System.out.println("╔══════════════════════════════════════════════════╗");
		System.out.println("║  Entrando Thread 3 (Escritura)                   ║");
		System.out.println("╚══════════════════════════════════════════════════╝");

		JsonWriter jsonWriter = null;
		BufferedOutputStream bufferedOutputStream = null;
		JsonArrayBuilder constructorArrayJson = Json.createArrayBuilder();
		

		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("pelis.json"));
			jsonWriter = Json.createWriter(bufferedOutputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!theQueue.isLast()) {
			JsonObject objJson = this.theQueue.getObject();
			objectProcessed++;
			constructorArrayJson.add(objJson);
		}

		jsonWriter.writeArray(constructorArrayJson.build());
		jsonWriter.close();

	}
}
