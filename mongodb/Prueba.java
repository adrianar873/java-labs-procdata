package mongodb;

import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.ReadPreference;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import io.github.cdimascio.dotenv.Dotenv;

public class Prueba {
	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		String password = dotenv.get("DB_Password");

		String uri = "mongodb+srv://adrianar:" + password
				+ "@cluster0.hgcsubk.mongodb.net/?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(uri)) {

			MongoDatabase database = mongoClient.getDatabase("sample_mflix");
			MongoCollection<Document> collection = database.getCollection("movies");

			Document doc = new Document("title", "Chanfaina").append("director", "SansaLabs");
			collection.withReadPreference(ReadPreference.primary()).insertOne(doc);
			Document search = collection.withReadPreference(ReadPreference.secondary()).find(eq("title", "Chanfaina"))
					.first();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
