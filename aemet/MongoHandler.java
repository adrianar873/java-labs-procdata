package aemet;

import io.github.cdimascio.dotenv.Dotenv;
import javax.json.JsonArray;
import javax.json.JsonObject;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Maneja la conexión e inserción en MongoDB Atlas.
 */
public class MongoHandler {

	private final MongoDatabase database;

	public MongoHandler() {
		Dotenv dotenv = Dotenv.load();
		String password = dotenv.get("DB_Password");
		String uri = "mongodb+srv://adrianar:" + password
				+ "@cluster0.hgcsubk.mongodb.net/?retryWrites=true&w=majority";

		MongoClient mongoClient = MongoClients.create(uri);
		this.database = mongoClient.getDatabase("aemetDB");
	}

	/**
	 * Inserta un JsonArray en la colección especificada.
	 *
	 * @param collectionName Nombre de la colección MongoDB
	 * @param array          JsonArray a insertar
	 */
	public void insertJsonArray(String collectionName, JsonArray array) {
		MongoCollection<Document> collection = database.getCollection(collectionName);

		for (JsonObject obj : array.getValuesAs(JsonObject.class)) {
			collection.insertOne(Document.parse(obj.toString()));
		}

		System.out.println("Insertados " + array.size() + " documentos en MongoDB.");
	}
}
